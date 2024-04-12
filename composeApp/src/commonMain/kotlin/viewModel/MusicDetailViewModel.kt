package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import commonShare.loadTimestamp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.MusicModel
import model.PlayHistoryModel
import model.PlayListModel
import model.TopicModel
import utils.helper.FirebaseMusicsHelper
import utils.helper.FirebasePlayHelper
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class MusicDetailViewModel: BaseViewModel() {
    // Save list topic
    val listTopic = mutableStateListOf<TopicModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save list music same singer
    val listMusicsSameSinger = mutableStateListOf<MusicModel>()

    // Save for check
    var saveMusic = MusicModel()

    // Player helper
    private val firebasePlayHelper = FirebasePlayHelper()

    // User helper
    private val firebaseUserHelper = FirebaseUserHelper()

    // Save playlists
    val listPlaylist = mutableStateListOf<PlayListModel>()

    /**
     * Load more information for show music detail
     */
    fun loadInformationMore(musicModel: MusicModel) {
        saveMusic = musicModel
        workingWithApiHaveDialog(
            service = {
                val topics = firebaseMusicsHelper.loadListTopicOnFB().first()
                listTopic.clear()
                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                musicModel.topicId.forEach {item ->
                    val topic = topics.find { topic -> item == topic.id }
                    topic?.let {
                        listTopic.add(topic)
                    }
                }
                val newList = musics.filter { item->
                    item.singerId == musicModel.singerId && item.id != musicModel.id
                }
                newList.forEach { item -> item.singerModel = musicModel.singerModel }
                newList
            },
            doOnBeforeService = {
                if (listPlaylist.isEmpty()) {
                    loadListPlaylist()
                }
            },
            doOnAfterService = {
                listMusicsSameSinger.clear()
                listMusicsSameSinger.addAll(it)
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Write data to firebase
     */
    fun writePlayMusicToHistory() {
        coroutineScope.launch {
            if (firebaseUserHelper.loadUserId().isNotEmpty()) {
                val playHistoryModel = PlayHistoryModel()
                playHistoryModel.musicId = saveMusic.id
                playHistoryModel.userId = firebaseUserHelper.loadUserId()
                playHistoryModel.timePlayed = loadTimestamp().toLong()
                firebasePlayHelper.writePlayHistoryToFB(playHistoryModel)
            }
        }
    }

    /**
     * Reload play list
     */
    private fun loadListPlaylist() {
        workingWithApiNonDialog(
            service = {
                val playlist = firebasePlayHelper.loadPlayListOfUser(firebaseUserHelper.loadUserId()).first()
                listPlaylist.clear()
                listPlaylist.addAll(playlist)
            },
            nextProgressStep = {}
        )
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
                listPlaylist.forEach { playlist ->
                    if (playlist.id == playlistId) {
                        // Update
                        playlist.listMusicsId.add(musicId)
                        playlist.updateAt = loadTimestamp().toLong()
                        firebasePlayHelper.updateMusicToPlaylist(playlist)
                    }
                }
            },
            doOnBeforeService = {},
            doOnAfterService = {},
        )
    }
}