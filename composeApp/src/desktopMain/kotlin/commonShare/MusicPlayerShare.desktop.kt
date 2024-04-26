package commonShare

import model.MusicModel

class DesktopMusicPlayerManager : MusicPlayerManager {
    override var onMusicPlayCallBack: OnMusicPlayCallBack? = null

    override fun initPlayer() {
    }

    override fun addMusicsToPlay(inputList: MutableList<MusicModel>) {
    }

    override fun addOnlyOneMusicToPlay(musicModel: MusicModel) {
    }

    override fun addMusicWithoutDuplicate(musicModel: MusicModel) {
    }

    override fun play(duration: Long?) {
    }

    override fun pause() {
    }

    override fun release() {
    }

    override fun destroy() {
    }

    override fun clearListPlay() {
    }

    override fun loadListPlay(): MutableList<MusicModel> {
        return arrayListOf()
    }

    override fun playOnBackground(isPlayOnBackground: Boolean) {
    }

    override fun isPlayed(): Boolean {
        return false
    }

    override fun playNext() {
    }

    override fun playPrevious() {
    }

    override fun repeatMusic(isRepeat: Boolean) {
    }

    override fun shuffleALlMedia(isShuffle: Boolean) {
    }

    override fun currentItemPlayer(): MusicModel? {
        return null
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