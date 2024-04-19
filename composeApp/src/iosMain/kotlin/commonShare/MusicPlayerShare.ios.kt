package commonShare

import model.MusicModel

actual fun loadMusicPlayerControl(): MusicPlayerManager {
    return IOsMusicPlayer()
}

class IOsMusicPlayer : MusicPlayerManager {

    override var onMusicPlayCallBack: OnMusicPlayCallBack? = null

    override fun initPlayer() {
    }

    override fun addMusicToPlay(inputList : MutableList<MusicModel>) {
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

    override fun playOnBackground(isPlayOnBackground: Boolean) {
    }

    override fun loadListPlay(): MutableList<MusicModel> {
        return arrayListOf()
    }

    override fun isPlayed(): Boolean {
        return true
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