package commonShare

import co.touchlab.kermit.Logger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import model.MusicModel
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.AVQueuePlayer
import platform.AVFoundation.asset
import platform.AVFoundation.currentItem
import platform.AVFoundation.currentTime
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.CoreMedia.CMTimeGetSeconds
import platform.CoreMedia.CMTimeMake
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSURL
import platform.Foundation.addObserver
import platform.Foundation.removeObserver
import platform.darwin.NSObject

import Bridge.*
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ObjCClass
import objcnames.classes.Protocol
import platform.darwin.NSUInteger

actual fun loadMusicPlayerControl(): MusicPlayerManager {
    return IOsMusicPlayer()
}

class IOsMusicPlayer : MusicPlayerManager {

    override var onMusicPlayCallBack: OnMusicPlayCallBack? = null

    // Save list to play
    private val listItemPlayer = mutableListOf<MusicModel>()
    private val listAvPlayerItem = mutableListOf<AVPlayerItem>()

    // Player
    private var avplayer : AVQueuePlayer? = null

    // Save is play
    private var isPlaying = false

    // Handle observer current item change
    private val observerCurrentItem = Observer()

    // Handle observer status of player
    private val observerStatus = Observer()

    // Save repeat
    private var isRepeatAllMusic = false

    override fun initPlayer() {
        avplayer = AVQueuePlayer()
    }

    private var durationPlayed = 0L

    private val jobRunningPlay = CoroutineScope(Dispatchers.IO).launch {
        flowRunningHandel.cancellable().collect {}
    }

    @OptIn(ExperimentalForeignApi::class)
    private val flowRunningHandel = flow {
        while (true) {
            if (isPlaying) {
                try {
                    avplayer?.let {
                        durationPlayed = (CMTimeGetSeconds(it.currentTime()) * 1000).toLong()
                        if (it.currentItem != null) {
                            val durationMusic = (CMTimeGetSeconds(it.currentItem!!.asset.duration)).toLong() * 1000L

                            Logger.e("durationPlay : $durationPlayed, durationMusic: $durationMusic")

                            if (durationPlayed < durationMusic) {
                                currentItemPlayer()?.let {music ->
                                    onMusicPlayCallBack?.onPlayingItem(durationPlayed, durationMusic, music.id)
                                    emit(music)
                                }
                            } else {
                                currentItemPlayer()?.let {music ->
                                    onMusicPlayCallBack?.onPlayDone(music.id)
                                }
                            }
                        } else {
                            // Play end all
                            Logger.e("Play end all music on playlist")
                            if (isRepeatAllMusic) {
                                Logger.e("Repeat")
                                avplayer?.removeAllItems()
                                listAvPlayerItem.clear()
                                listItemPlayer.forEach {music ->
                                    val playerItem = AVPlayerItem(NSURL(string = music.url))
                                    listAvPlayerItem.add(playerItem)
                                    val lastItem = avplayer?.items()?.last()
                                    avplayer?.insertItem(playerItem, lastItem as AVPlayerItem?)
                                }
                                avplayer?.play()
                            } else {
                                onMusicPlayCallBack?.onPlayDone(listItemPlayer.last().id)
                                isPlaying = false
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Logger.e("Nothing to send, music is not play")
            }
            delay(1000L)
        }
    }


    @OptIn(ExperimentalForeignApi::class)
    override fun addMusicsToPlay(inputList : MutableList<MusicModel>) {
        inputList.forEach { musicModel ->
            val playerItem = AVPlayerItem(NSURL(string = musicModel.url))
            val lastItem = avplayer?.items()?.last()
            avplayer?.insertItem(playerItem, lastItem as AVPlayerItem?)
            listAvPlayerItem.add(playerItem)
        }
        listItemPlayer.addAll(inputList)
        Logger.e("Size of music addOnlyOneMusicToPlay: ${listItemPlayer.size} ")
        // Prepare
        val seekTime  = CMTimeMake(0, 1)
        avplayer?.seekToTime(seekTime)
    }

    override fun addOnlyOneMusicToPlay(musicModel: MusicModel) {
        listItemPlayer.clear()
        listItemPlayer.add(musicModel)
        listAvPlayerItem.clear()
        Logger.e("Size of music addOnlyOneMusicToPlay: ${listItemPlayer.size} ")
        avplayer?.removeAllItems()
        val playerItem = AVPlayerItem(NSURL(string = musicModel.url))
        listAvPlayerItem.add(playerItem)
        avplayer?.replaceCurrentItemWithPlayerItem(playerItem)
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun addMusicWithoutDuplicate(musicModel: MusicModel) {
        var find = false
        listItemPlayer.forEachIndexed { _, music ->
            if (music.id == musicModel.id) {
                find = true
                val seekTime  = CMTimeMake(0, 1)
                avplayer?.seekToTime(seekTime)
            }
        }
        if (!find) {
            listItemPlayer.add(musicModel)
            val playerItem = AVPlayerItem(NSURL(string = musicModel.url))
            val lastItem = avplayer?.items()?.last()
            avplayer?.insertItem(playerItem, lastItem as AVPlayerItem?)
            listAvPlayerItem.add(playerItem)

            // Set the media item to be played.
            val seekTime  = CMTimeMake(0, 1)
            avplayer?.seekToTime(seekTime)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun play(duration: Long?) {
        Logger.e("play : $duration")
        duration?.let {
            val seekTime  = CMTimeMake(it / 1000, 1)
            avplayer?.seekToTime(seekTime)
        }
        avplayer?.play()
        isPlaying = true
        jobRunningPlay.start()

        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = avplayer?.currentItem,
            queue = null,
        ) {
            Logger.e("Track completed")
        }

        avplayer?.addObserver(
            observerCurrentItem, forKeyPath = "currentItem", options = 1u, context = null)

        avplayer?.addObserver(
            observerStatus, forKeyPath = "status", options = 1u, context = null)
    }

    override fun pause() {
        avplayer?.pause()
        isPlaying = false
        avplayer?.removeObserver(observerCurrentItem, forKeyPath = "currentItem")
        avplayer?.removeObserver(observerStatus, forKeyPath = "status")
    }

    override fun release() {
        avplayer = null
        isPlaying = false
        jobRunningPlay.cancel()
        avplayer?.removeObserver(observerCurrentItem, forKeyPath = "currentItem")
        avplayer?.removeObserver(observerStatus, forKeyPath = "status")
    }

    override fun destroy() {
    }

    override fun clearListPlay() {
        avplayer?.removeObserver(observerCurrentItem, forKeyPath = "currentItem")
        avplayer?.removeObserver(observerStatus, forKeyPath = "status")
        listItemPlayer.clear()
        avplayer?.removeAllItems()
        listAvPlayerItem.clear()
    }

    override fun playOnBackground(isPlayOnBackground: Boolean) {
    }

    override fun loadListPlay(): MutableList<MusicModel> {
        return listItemPlayer
    }

    override fun isPlayed(): Boolean {
        return isPlaying
    }

    override fun playNext() {
        avplayer?.advanceToNextItem()
    }

    override fun playPrevious() {
        listAvPlayerItem.forEachIndexed { index, avPlayerItem ->
            val track = avplayer?.currentItem
            if (avPlayerItem == track && index > 0) {
                val itemInsert = listAvPlayerItem[index - 1]
                avplayer?.replaceCurrentItemWithPlayerItem(itemInsert)
            }
        }
    }

    override fun repeatMusic(isRepeat: Boolean) {
        isRepeatAllMusic = isRepeat
    }

    override fun shuffleALlMedia(isShuffle: Boolean) {
    }

    override fun currentItemPlayer(): MusicModel? {
        return try {
            if (listItemPlayer.size > 0) {
                listItemPlayer[loadCurrentIndexPlayer()]
            } else {
                return null
            }
        } catch (e:Exception) {
            Logger.e("currentItemPlayer : error : $e")
            null
        }
    }

    /**
     * Load current index
     *
     * @return
     */
    private fun loadCurrentIndexPlayer(): Int {
        var indexReturn = -1
        listAvPlayerItem.forEachIndexed { index, avPlayerItem ->
            val track = avplayer?.currentItem
            if (avPlayerItem == track) {
                indexReturn = index
                return indexReturn
            }
        }
        return indexReturn
    }
}

@OptIn(ExperimentalForeignApi::class)
class Observer : MusicObserverProtocol, NSObject() {

    @OptIn(ExperimentalForeignApi::class)
    override fun observeValueForKeyPath(
        keyPath: String,
        ofObject: Any,
        change: Map<Any?, *>,
        context: COpaquePointer?
    ) {
        Logger.e("keyPath $keyPath")
        Logger.e("ofObject $ofObject")
        Logger.e("change $change")
        Logger.e("context $context")
    }
}