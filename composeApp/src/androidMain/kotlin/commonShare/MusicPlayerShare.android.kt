package commonShare

import android.os.Handler
import android.os.Looper
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.dongnh.musicplayer.AndroidMusicPlayerSingleton
import model.MusicModel
import timber.log.Timber

actual fun loadMusicPlayerControl(): MusicPlayerManager {
    return AndroidMusicPlayer()
}

class AndroidMusicPlayer : MusicPlayerManager {

    // Save list to play
    private val listItemPlayer = mutableListOf<MusicModel>()

    // Player
    private val playListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Timber.e("onIsPlayingChanged $isPlaying")
            if (isPlaying) {
                // Active playback.
                // TODO : callback
            } else {
                // Not playing because playback is paused, ended, suppressed, or the player
                // is buffering, stopped or failed. Check player.playWhenReady,
                // player.playbackState, player.playbackSuppressionReason and
                // player.playerError for details.
                // TODO : callback
            }
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            super.onRepeatModeChanged(repeatMode)
            Timber.e("onRepeatModeChanged Mode: $repeatMode")
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            Timber.e("onPlayerError $error")
            val cause = error.cause
            if (cause is HttpDataSource.HttpDataSourceException) {
                // An HTTP error occurred.
                if (cause is HttpDataSource.InvalidResponseCodeException) {
                    // Cast to InvalidResponseCodeException and retrieve the response code, message
                    // and headers.
                    Timber.e(cause)
                } else {
                    // Try calling httpError.getCause() to retrieve the underlying cause, although
                    // note that it may be null.
                    Timber.e(cause)
                }
            }
        }

        override fun onEvents(player: Player, events: Player.Events) {
            Timber.e("onEvents : $events")
            if (events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED) ||
                events.contains(Player.EVENT_PLAY_WHEN_READY_CHANGED)) {
                // TODO : callback
            }
        }

        override fun onMediaItemTransition(
            mediaItem: MediaItem?,
            @Player.MediaItemTransitionReason reason: Int,
        ) {
            Timber.e("onMediaItemTransition")
            // TODO : callback for update UI player to next item
        }

        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            Timber.e("onTimelineChanged")
            super.onTimelineChanged(timeline, reason)
            if (reason == Player.TIMELINE_CHANGE_REASON_SOURCE_UPDATE) {
                // duration is available now:
                // player.duration
                exoPlayer.currentPosition
                exoPlayer.duration
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            Timber.e("onPlaybackStateChanged state : $playbackState")
            if (playbackState == ExoPlayer.STATE_READY) {
                Timber.e("onPlaybackStateChanged total duration of music: ${exoPlayer.currentPosition}")
                currentItemPlayer()?.let {music ->
                    onMusicPlayCallBack?.onPlayingItem(exoPlayer.currentPosition / 1000, exoPlayer.duration, music.id)
                }
                looper.postDelayed(runnable, 1000)
            } else if (playbackState == Player.STATE_ENDED) {
                currentItemPlayer()?.let {music ->
                    onMusicPlayCallBack?.onPlayingItem(exoPlayer.duration, exoPlayer.duration, music.id)
                    onMusicPlayCallBack?.onPlayDone(music.id)
                }
            }
        }
    }

    // Player
    lateinit var exoPlayer : ExoPlayer

    override var onMusicPlayCallBack: OnMusicPlayCallBack? = null

    private var durationPlayed = 0L

    // Handle
    private val looper =  Handler(Looper.getMainLooper())

    // Runnable to handle duration
    val runnable: Runnable =  object : Runnable {
        override fun run() {
            if (::exoPlayer.isInitialized && exoPlayer.isPlaying) {
                durationPlayed = exoPlayer.currentPosition / 1000
                currentItemPlayer()?.let {music ->
                    onMusicPlayCallBack?.onPlayingItem(exoPlayer.currentPosition, exoPlayer.duration, music.id)
                }
                looper.postDelayed(this, 1000)
            } else {
                looper.removeCallbacks(this)
            }
        }
    }

    override fun initPlayer() {
        if (!::exoPlayer.isInitialized) {
            AndroidMusicPlayerSingleton.mainActivity?.let {
                exoPlayer = ExoPlayer.Builder(it).build()
                exoPlayer.addListener(playListener)
            }
        }
    }

    override fun addMusicsToPlay(inputList: MutableList<MusicModel>) {
        inputList.forEach { musicModel ->
            // Build the media item.
            val mediaItem = MediaItem.fromUri(musicModel.url)

            // Set the media item to be played.
            exoPlayer.addMediaItem(mediaItem)
        }
        listItemPlayer.addAll(inputList)
        // Prepare
        exoPlayer.prepare()
        exoPlayer.seekTo(0, 0L)
    }

    override fun addOnlyOneMusicToPlay(musicModel: MusicModel) {
        listItemPlayer.clear()
        listItemPlayer.add(musicModel)
        val mediaItem = MediaItem.fromUri(musicModel.url)

        // Set the media item to be played.
        exoPlayer.setMediaItem(mediaItem)
        // Prepare
        exoPlayer.prepare()
    }

    override fun addMusicWithoutDuplicate(musicModel: MusicModel) {
        var find = false
        listItemPlayer.forEachIndexed { index, music ->
            if (music.id == musicModel.id) {
                find = true
                exoPlayer.seekTo(index, 0L)
            }
        }
        if (!find) {
            listItemPlayer.add(musicModel)
            val mediaItem = MediaItem.fromUri(musicModel.url)

            // Set the media item to be played.
            exoPlayer.setMediaItem(mediaItem)
            // Prepare
            exoPlayer.prepare()
        }
    }

    override fun play(duration: Long?) {
        duration?.let {
            exoPlayer.seekTo(it)
        }
        exoPlayer.playWhenReady = true
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun destroy() {
        exoPlayer
    }

    override fun clearListPlay() {
        exoPlayer.clearMediaItems()
        listItemPlayer.clear()
    }

    override fun playOnBackground(isPlayOnBackground: Boolean) {
    }

    override fun loadListPlay(): MutableList<MusicModel> {
        return listItemPlayer
    }

    override fun isPlayed(): Boolean {
        return exoPlayer.isPlaying
    }

    override fun playNext() {
        if (exoPlayer.hasNextMediaItem()) {
            exoPlayer.seekToNext()
        }
    }

    @OptIn(UnstableApi::class)
    override fun playPrevious() {
        if (exoPlayer.hasPreviousMediaItem()) {
            exoPlayer.seekToPrevious()
        }
    }

    override fun repeatMusic(isRepeat: Boolean) {
        if (isRepeat) {
            exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
        } else {
            exoPlayer.repeatMode = Player.REPEAT_MODE_OFF
        }
    }

    override fun shuffleALlMedia(isShuffle: Boolean) {
        exoPlayer.shuffleModeEnabled = isShuffle
    }

    override fun currentItemPlayer(): MusicModel? {
        if (!::exoPlayer.isInitialized) {
            return null
        }

        return try {
            listItemPlayer[exoPlayer.currentMediaItemIndex]
        } catch (e:Exception) {
            Timber.e(e)
            null
        }
    }
}