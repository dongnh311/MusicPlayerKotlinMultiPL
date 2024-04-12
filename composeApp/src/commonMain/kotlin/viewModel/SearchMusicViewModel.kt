package viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import base.BaseViewModel
import commonShare.loadTimestamp
import kotlinx.coroutines.flow.first
import model.MusicModel
import model.PlayListModel
import utils.helper.FirebaseMusicsHelper
import utils.helper.FirebasePlayHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SearchMusicViewModel: BaseViewModel() {

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // List all music
    val listAllMusic = mutableStateListOf<MusicModel>()

    // List music to search
    var listMusicSearch = mutableStateListOf<MusicModel>()

    // Load play
    private val firebasePlayHelper = FirebasePlayHelper()

    // Save playlist
    var playlistModel = PlayListModel()

    /**
     * Load all music
     */
    fun loadAllMusic() {
        workingWithApiHaveDialog(
            service = {
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()
                musics.forEach { music ->
                    music.singerModel = singers.find { singer -> singer.id == music.singerId }
                }

                musics
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listAllMusic.clear()
                listAllMusic.addAll(it)
                listAllMusic.forEach { item ->
                    listMusicSearch.add(item)
                }
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Search music
     *
     * @param inputSearch
     */
    fun searchMusicByName(inputSearch: String) {
        listMusicSearch.clear()
        listMusicSearch.addAll(listAllMusic.filter { musicModel ->
            musicModel.name.contains(inputSearch)
        } as SnapshotStateList<MusicModel>)
    }

    /**
     * Add music to playlist
     *
     * @param playlistId
     * @param musicId
     */
    fun addMusicToPlaylist(playlistId: String, musicId: String) {
        workingWithApiHaveDialog(
            service = {
                if (playlistModel.id != playlistId) {
                    playlistModel = firebasePlayHelper.findPlaylistWithId(playlistId).first()
                }

                // Update
                playlistModel.listMusicsId.add(musicId)
                playlistModel.updateAt = loadTimestamp().toLong()
                firebasePlayHelper.updateMusicToPlaylist(playlistModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {},
            onErrorThrowable = {}
        )
    }
}