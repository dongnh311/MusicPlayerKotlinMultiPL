package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import commonShare.loadFireBaseStorage
import commonShare.loadTimestamp
import const.FIREBASE_STORAGE_PLAYLIST_THUMB
import kotlinx.coroutines.flow.first
import model.MusicModel
import model.PlayListModel
import utils.helper.FirebaseMusicsHelper
import utils.helper.FirebasePlayHelper
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PlaylistViewModel: BaseViewModel() {

    // Load play
    private val firebasePlayHelper = FirebasePlayHelper()

    // User helper
    private val firebaseUserHelper = FirebaseUserHelper()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Firebase native
    private val firebaseStorageShare = loadFireBaseStorage()

    // List playlist
    val listPlayList = mutableStateListOf<PlayListModel>()

    // Save all music
    private val listAllMusic = mutableListOf<MusicModel>()

    // Save music on playlist
    val listMusicOnPlaylist = mutableStateListOf<MusicModel>()

    /**
     * Load list playlist
     */
    fun loadListPlaylist() {
        workingWithApiHaveDialog(
            service = {
                firebasePlayHelper.loadPlayListOfUser(firebaseUserHelper.loadUserId()).first()
            },
            doOnBeforeService = {

            },
            doOnAfterService = {
                listPlayList.clear()
                listPlayList.addAll(it)
                if (listPlayList.isNotEmpty()) {
                    loadListMusicOnPlayListNoneDialog(listPlayList.first())
                }
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Load all music on playlist
     *
     * @param playListModel
     */
    fun loadListMusicOnPlayList(playListModel: PlayListModel) {
        workingWithApiHaveDialog(
            service = {
                if (listAllMusic.isEmpty()) {
                    val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                    val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                    // Make list music
                    musics.forEach {music ->
                        music.singerModel = singers.find { singer -> singer.id == music.singerId }
                    }

                    listAllMusic.clear()
                    listAllMusic.addAll(musics)
                }
                listAllMusic.filter { music ->
                    playListModel.listMusicsId.contains(music.id)
                }

            },
            doOnBeforeService = {},
            doOnAfterService = {
                listMusicOnPlaylist.clear()
                listMusicOnPlaylist.addAll(it)
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Load all music on playlist without dialog
     *
     * @param playListModel
     */
    private fun loadListMusicOnPlayListNoneDialog(playListModel: PlayListModel) {
        workingWithApiNonDialog(
            service = {
                if (listAllMusic.isEmpty()) {
                    val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                    val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                    // Make list music
                    musics.forEach {music ->
                        music.singerModel = singers.find { singer -> singer.id == music.singerId }
                    }

                    listAllMusic.clear()
                    listAllMusic.addAll(musics)
                }
                listAllMusic.filter { music ->
                    playListModel.listMusicsId.contains(music.id)
                }

            },
            nextProgressStep = {
                listMusicOnPlaylist.clear()
                listMusicOnPlaylist.addAll(it)
            }
        )
    }

    /**
     * Create new playlist
     *
     * @param playListName
     */
    fun createNewPlayList(playListName: String) {
        workingWithApiHaveDialog(
            service = {
                val playListModel = PlayListModel()
                playListModel.name = playListName
                playListModel.userId = firebaseUserHelper.loadUserId()
                playListModel.createAt = loadTimestamp().toLong()
                firebasePlayHelper.writeNewPlaylistToFB(playListModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                // Reload list
                loadListPlaylist()
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Update information of playlist
     *
     * @param playListModel
     */
    fun updateInformationPlaylist(playListModel: PlayListModel) {
        workingWithApiHaveDialog(
            service = {
                firebasePlayHelper.updateInformationPlaylist(playListModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                loadListPlaylist()
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Delete playlist
     *
     * @param playListModel
     */
    fun deletePlaylist(playListModel: PlayListModel) {
        workingWithApiHaveDialog(
            service = {
                firebasePlayHelper.deletePlayList(playListModel.id)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                loadListPlaylist()
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Update new thumb
     *
     * @param playListModel
     */
    fun uploadImageFileToThumb(playListModel: PlayListModel) {
        workingWithApiHaveDialog(
            service = {
                val newPath = firebaseStorageShare.uploadImageFileToStorage(playListModel.thumbnail,
                    "$FIREBASE_STORAGE_PLAYLIST_THUMB/${playListModel.id}").first()
                playListModel.thumbnail = newPath
                playListModel.updateAt = loadTimestamp().toLong()
                firebasePlayHelper.updateInformationPlaylist(playListModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                loadListPlaylist()
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Delete music on playlist
     *
     * @param playListModel
     * @param musicId
     */
    fun deleteMusicOnPlayList(playListModel: PlayListModel, musicId: String) {
        workingWithApiHaveDialog(
            service = {
                playListModel.listMusicsId.remove(musicId)
                playListModel.updateAt = loadTimestamp().toLong()
                firebasePlayHelper.updateMusicToPlaylist(playListModel)
            },
            doOnBeforeService = { loadListMusicOnPlayListNoneDialog(playListModel)},
            doOnAfterService = {
            },
            onErrorThrowable = {}
        )
    }
}