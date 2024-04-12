package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import kotlinx.coroutines.flow.first
import model.PlayHistoryModel
import utils.helper.FirebaseMusicsHelper
import utils.helper.FirebasePlayHelper
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PlayHistoryViewModel: BaseViewModel() {

    // Load play
    private val firebasePlayHelper = FirebasePlayHelper()

    // Save list played
    val listPlayHistory = mutableStateListOf<PlayHistoryModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // User helper
    private val firebaseUserHelper = FirebaseUserHelper()

    /**
     * Load list played
     */
    fun loadListPlayed() {
        workingWithApiHaveDialog(
            service = {
                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                musics.forEach { music ->
                    music.singerModel = singers.find { singer -> music.singerId == singer.id }
                }

                val playHistory = firebasePlayHelper.loadPlayHistoryList(firebaseUserHelper.loadUserId()).first()
                playHistory.forEach { history ->
                    history.musicModel = musics.find { music-> music.id == history.musicId }
                }
                playHistory
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listPlayHistory.clear()
                listPlayHistory.addAll(it)
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Delete all history
     *
     */
    fun deleteAllHistory() {
        workingWithApiHaveDialog(
            service = {
                firebasePlayHelper.deleteAllPlayHistory(firebaseUserHelper.loadUserId())
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listPlayHistory.clear()
            }
        )
    }
}