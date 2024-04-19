package commonShare

import model.MusicModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 19/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */


/**
 * Player control
 */
interface MusicPlayerManager {

    // Callback
    var onMusicPlayCallBack: OnMusicPlayCallBack?

    /**
     * Init player
     */
    fun initPlayer()

    /**
     * Add music to play
     *
     * @param inputList
     */
    fun addMusicToPlay(inputList : MutableList<MusicModel>)

    /**
     * Play music
     *
     * @param duration
     */
    fun play(duration : Long?)

    /**
     * Pause
     *
     */
    fun pause()

    /**
     * Release player
     *
     */
    fun release()

    /**
     * Destroy
     *
     */
    fun destroy()

    /**
     * Clear all play list
     *
     */
    fun clearListPlay()

    /**
     * Load list to play
     *
     * @return
     */
    fun loadListPlay() : MutableList<MusicModel>

    /**
     * Make it play on back ground
     *
     * @param isPlayOnBackground
     */
    fun playOnBackground(isPlayOnBackground: Boolean)

    /**
     * Check is playing or not
     *
     * @return
     */
    fun isPlayed() : Boolean

    /**
     * Play to next
     *
     */
    fun playNext()

    /**
     * Play to Previous
     *
     */
    fun playPrevious()

    /**
     * Repeat music
     *
     * @param isRepeat
     */
    fun repeatMusic(isRepeat: Boolean)

    /**
     * Shuffle
     *
     * @param isShuffle
     */
    fun shuffleALlMedia(isShuffle : Boolean)

    /**
     * Load item current is playing
     *
     * @return
     */
    fun currentItemPlayer() : MusicModel?
}

interface OnMusicPlayCallBack {

    fun onPlayingItem(durationPlaying: Long, totalDuration: Long, musicId: String)

    fun onPlayDone(musicId: String)

    fun onMoveNextItem(musicModel: MusicModel)

    fun onMovePrevious(musicModel: MusicModel)
}

/**
 * Load music player
 *
 * @return
 */
expect fun loadMusicPlayerControl() : MusicPlayerManager
