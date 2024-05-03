package commonShare

import co.touchlab.kermit.Logger
import model.MusicModel
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.CallbackMediaListPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaListPlayerComponent
import uk.co.caprica.vlcj.player.list.MediaListPlayer
import java.util.Locale


class DesktopMusicPlayerManager : MusicPlayerManager {
    override var onMusicPlayCallBack: OnMusicPlayCallBack? = null

    private var mediaPlayer: MediaPlayer? = null
    private var mediaListPlayer: MediaListPlayer? = null

    private var durationPlayed = 0L

    // Save list to play
    private val listItemPlayer = mutableListOf<MusicModel>()

    // Save current item playing
    private var itemIsPlaying: MusicModel? = null

    override fun initPlayer() {
        NativeDiscovery().discover()
        if (mediaListPlayer == null) {
            // see https://github.com/caprica/vlcj/issues/887#issuecomment-503288294 for why we're using CallbackMediaPlayerComponent for macOS.
            mediaListPlayer = if (isMacOS()) {
                CallbackMediaListPlayerComponent().mediaListPlayer()
            } else {
                EmbeddedMediaListPlayerComponent().mediaListPlayer()
            }
        }
        mediaPlayer = mediaListPlayer?.mediaPlayer()?.mediaPlayer()
        mediaPlayer!!.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun finished(mediaPlayer: MediaPlayer) {
                Logger.e("initPlayer finished")
                itemIsPlaying?.let { music ->
                    onMusicPlayCallBack?.onPlayDone(music.id)

                    // Move to next
                    if (listItemPlayer.size > 1) {
                        val nextItem = findItemToPlay(findIndexOfItemPlaying(music))
                        nextItem?.let {next ->
                            mediaPlayer.media().play(next.url)
                        }
                    }
                }
            }

            override fun playing(mediaPlayer: MediaPlayer?) {
                super.playing(mediaPlayer)
                Logger.e("initPlayer playing")
            }

            override fun paused(mediaPlayer: MediaPlayer?) {
                super.paused(mediaPlayer)
                Logger.e("initPlayer paused")
            }

            override fun stopped(mediaPlayer: MediaPlayer?) {
                super.stopped(mediaPlayer)
                Logger.e("initPlayer stopped")
                mediaPlayer?.controls()?.pause()
            }

            override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
                super.timeChanged(mediaPlayer, newTime)
                Logger.e("initPlayer timeChanged")
                itemIsPlaying?.let { music ->
                    val durationMusic = if (mediaPlayer?.media()?.info() != null) {
                        mediaPlayer.media().info().duration()
                    } else {
                        music.duration.toLong() * 1000L
                    }
                    onMusicPlayCallBack?.onPlayingItem(newTime, durationMusic , music.id)
                }
            }

            override fun mediaPlayerReady(mediaPlayer: MediaPlayer?) {
                super.mediaPlayerReady(mediaPlayer)
                Logger.e("initPlayer mediaPlayerReady")
            }

            override fun error(mediaPlayer: MediaPlayer?) {
                super.error(mediaPlayer)
                Logger.e("initPlayer error")
            }

            override fun lengthChanged(mediaPlayer: MediaPlayer?, newLength: Long) {
                super.lengthChanged(mediaPlayer, newLength)
                Logger.e("initPlayer lengthChanged $newLength")
            }
        })
    }

    override fun addMusicsToPlay(inputList: MutableList<MusicModel>) {
        pauseMediaIfNeed()
        inputList.forEach {
            mediaListPlayer?.list()?.media()?.add(it.url)
        }

        listItemPlayer.addAll(inputList)
        // Prepare
        itemIsPlaying = listItemPlayer.first()
        mediaPlayer?.media()?.startPaused(itemIsPlaying!!.url)
        mediaPlayer?.controls()?.setTime(0)
    }

    override fun addOnlyOneMusicToPlay(musicModel: MusicModel) {
        pauseMediaIfNeed()

        mediaListPlayer?.list()?.media()?.clear()
        listItemPlayer.clear()
        listItemPlayer.add(musicModel)
        mediaListPlayer?.list()?.media()?.add(musicModel.url)

        // Set the media item to be played.
        itemIsPlaying = musicModel
        mediaPlayer?.media()?.startPaused(musicModel.url)
        mediaPlayer?.controls()?.setTime(0)
    }

    override fun addMusicWithoutDuplicate(musicModel: MusicModel) {
        pauseMediaIfNeed()

        var find = false
        listItemPlayer.forEachIndexed { _, music ->
            if (music.id == musicModel.id) {
                find = true
                itemIsPlaying = musicModel
                mediaPlayer?.media()?.startPaused(musicModel.url)
            }
        }
        if (!find) {
            listItemPlayer.add(musicModel)
            mediaListPlayer?.list()?.media()?.add(musicModel.url)

            // Set the media item to be played.
            itemIsPlaying = musicModel
            mediaPlayer?.media()?.startPaused(musicModel.url)
        }

        mediaPlayer?.controls()?.setTime(0)
    }

    override fun play(duration: Long?) {
        duration?.let {
            mediaPlayer?.controls()?.setTime(duration)
        }
        mediaPlayer?.controls()?.play()
    }

    override fun pause() {
        mediaPlayer?.controls()?.pause()
    }

    override fun release() {
        mediaPlayer?.controls()?.stop()
        mediaPlayer?.release()
    }

    override fun destroy() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun clearListPlay() {
        mediaListPlayer?.list()?.media()?.clear()
        listItemPlayer.clear()
        mediaListPlayer?.list()?.media()
    }

    override fun loadListPlay(): MutableList<MusicModel> {
        return listItemPlayer
    }

    override fun playOnBackground(isPlayOnBackground: Boolean) {
    }

    override fun isPlayed(): Boolean {
        return mediaPlayer?.status()?.isPlaying ?: false
    }

    override fun playNext() {
        // Move to next
        if (listItemPlayer.size > 1 && itemIsPlaying != null) {
            val index = findIndexOfItemPlaying(itemIsPlaying!!)
            val nextItem = findItemToPlay(index)
            nextItem?.let { next ->
                itemIsPlaying = next
                //mediaPlayer?.media()?.play(next.url)
            }
            mediaPlayer?.controls()?.setPosition((index + 1).toFloat())
            mediaPlayer?.controls()?.setTime(0)
            mediaPlayer?.controls()?.play()
        }
    }

    override fun playPrevious() {
        val index = findIndexOfItemPlaying(itemIsPlaying!!)
        if (index > 0) {
           val item =  findItemToPlay(index)
            item?.let { next ->
                itemIsPlaying = next
                //mediaPlayer?.media()?.play(next.url)
            }
            mediaPlayer?.controls()?.setPosition((index - 1).toFloat())
            mediaPlayer?.controls()?.setTime(0)
            mediaPlayer?.controls()?.play()
        }
    }

    override fun repeatMusic(isRepeat: Boolean) {
        mediaPlayer?.controls()?.repeat = isRepeat
    }

    override fun shuffleALlMedia(isShuffle: Boolean) {
    }

    override fun currentItemPlayer(): MusicModel? {
        return itemIsPlaying
    }

    private fun isMacOS(): Boolean {
        val os = System.getProperty("os.name", "generic").lowercase(Locale.ENGLISH)
        return os.indexOf("mac") >= 0 || os.indexOf("darwin") >= 0
    }

    /**
     * Find index of item is playing
     *
     * @param musicModel
     * @return
     */
    private fun findIndexOfItemPlaying(musicModel: MusicModel) : Int {
        val indexItem = 0
        listItemPlayer.forEachIndexed { index, music ->
            if (music.id == musicModel.id) {
                return index
            }
        }
        return indexItem
    }

    /**
     * Find next item to play
     *
     * @param index
     * @return
     */
    private fun findItemToPlay(index: Int) : MusicModel? {
        try {
            return listItemPlayer[index]
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Pause media
     *
     */
    private fun pauseMediaIfNeed() {
        if (mediaPlayer?.status()?.isPlaying == true) {
            mediaPlayer?.controls()?.stop()
        }
    }
}

/**
 * Load music player
 *
 * @return
 */
actual fun loadMusicPlayerControl(): MusicPlayerManager {
    return DesktopMusicPlayerManager()
}