package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import commonShare.loadFireBaseStorage
import commonShare.loadTimestamp
import const.FIREBASE_STORAGE_PLAYLIST_THUMB
import kotlinx.coroutines.flow.first
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

    /**
     * Load list playlist
     */
    fun loadListPlaylist() {
        workingWithApiHaveDialog(
            service = {
                firebasePlayHelper.loadPlayListOfUser(firebaseUserHelper.loadUserId()).first()
            },
            progressInBackground = {},
            progressInLayout = {
                listPlayList.clear()
                listPlayList.addAll(it)
            },
            onErrorThrowable = {}
        )
    }

    fun loadListMusicOnPlayList(playListModel: PlayListModel) {
        workingWithApiHaveDialog(
            service = {

            },
            progressInBackground = {},
            progressInLayout = {},
            onErrorThrowable = {}
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
            progressInBackground = {},
            progressInLayout = {
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
            progressInBackground = {},
            progressInLayout = {
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
            progressInBackground = {},
            progressInLayout = {
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
            progressInBackground = {},
            progressInLayout = {
                loadListPlaylist()
            },
            onErrorThrowable = {}
        )
    }
}