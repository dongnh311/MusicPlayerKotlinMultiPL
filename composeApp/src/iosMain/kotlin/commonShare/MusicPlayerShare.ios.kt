package commonShare

import co.touchlab.kermit.Logger
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.MusicModel
import platform.AVFoundation.AVPlayerItem
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
import platform.Foundation.NSURL

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
    private var playerItem: AVPlayerItem? = null

    // Save is play
    private var isPlaying = false

    override fun initPlayer() {
        avplayer = AVQueuePlayer()
    }

    private var durationPlayed = 0L

    private val jobRunningPlay = CoroutineScope(Dispatchers.IO).launch {
        Logger.e("jobRunningPlay : runnable running")
        runnable.run()
        delay(1000)
    }

    // Runnable to handle duration
    @OptIn(ExperimentalForeignApi::class)
    val runnable: Runnable =  object : Runnable {
        override fun run() {
            Logger.e("isPlaying : $isPlaying")
            if (isPlaying) {
                avplayer?.let {

                    durationPlayed = (CMTimeGetSeconds(it.currentTime()) * 1000).toLong()
                    val durationMusic = (CMTimeGetSeconds(it.currentItem!!.asset.duration)).toLong()
                    Logger.e("durationPlay : $durationPlayed, durationMusic: $durationMusic")

                    currentItemPlayer()?.let {music ->
                        onMusicPlayCallBack?.onPlayingItem(durationPlayed, durationMusic, music.id)
                    }
                }
            } else {
                Logger.e("cancel job")
                jobRunningPlay.cancel()
            }
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
        // Prepare
        val seekTime  = CMTimeMake(0, 1)
        avplayer?.seekToTime(seekTime)
    }

    override fun addOnlyOneMusicToPlay(musicModel: MusicModel) {
        listItemPlayer.clear()
        listItemPlayer.add(musicModel)
        avplayer?.removeAllItems()
        playerItem = AVPlayerItem(NSURL(string = musicModel.url))
        avplayer?.replaceCurrentItemWithPlayerItem(playerItem)
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun addMusicWithoutDuplicate(musicModel: MusicModel) {
        var find = false
        listItemPlayer.forEachIndexed { index, music ->
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
            val seekTime  = CMTimeMake(it, 1)
            avplayer?.seekToTime(seekTime)
        }
        avplayer?.play()
        isPlaying = true
        runBlocking {
            jobRunningPlay.join()
        }
    }

    override fun pause() {
        avplayer?.pause()
        isPlaying = false
        jobRunningPlay.cancel()
    }

    override fun release() {
        avplayer = null
        isPlaying = false
    }

    override fun destroy() {
    }

    override fun clearListPlay() {
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
            if (avPlayerItem == track) {
                val itemInsert = listAvPlayerItem[index]
                avplayer?.replaceCurrentItemWithPlayerItem(itemInsert)
            }
        }
    }

    override fun repeatMusic(isRepeat: Boolean) {
    }

    override fun shuffleALlMedia(isShuffle: Boolean) {
    }

    override fun currentItemPlayer(): MusicModel? {
        return try {
            listItemPlayer[loadCurrentIndexPlayer()]
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
                return index
            }
        }
        return indexReturn
    }
}