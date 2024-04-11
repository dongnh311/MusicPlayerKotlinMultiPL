package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import kotlinx.coroutines.flow.first
import model.MusicModel
import model.TopicModel
import utils.helper.FirebaseMusicsHelper

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
                    item.singerId == musicModel.singerId && item.id  != musicModel.id
                }
                newList.forEach { item -> item.singerModel = musicModel.singerModel }
                newList
            },
            progressInBackground = {},
            progressInLayout = {
                listMusicsSameSinger.clear()
                listMusicsSameSinger.addAll(it)
            },
            onErrorThrowable = {}
        )
    }

}