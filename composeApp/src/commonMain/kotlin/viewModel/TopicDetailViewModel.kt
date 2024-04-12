package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.first
import model.MusicModel
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class TopicDetailViewModel: BaseViewModel() {

    // Save list music
    val listMusicFollowTopic = mutableStateListOf<MusicModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save last topic
    var lastTopicFind = ""

    /**
     * Find music by topic
     *
     * @param topic
     */
    fun findListMusicByTopic(topic: String) {
        Logger.e("Find music by topic $topic")
        lastTopicFind = topic
        workingWithApiHaveDialog(
            service = {
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                val listMusicByTopic = mutableListOf<MusicModel>()
                // Load new image path
                // Find singer
                musics.forEach { item ->
                    item.singerModel = singers.find { singer -> singer.id == item.singerId }
                    if (item.topicId.contains(topic)) {
                        listMusicByTopic.add(item)
                    }
                }

                listMusicByTopic
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listMusicFollowTopic.clear()
                listMusicFollowTopic.addAll(it)
            },
            onErrorThrowable = {}
        )
    }
}