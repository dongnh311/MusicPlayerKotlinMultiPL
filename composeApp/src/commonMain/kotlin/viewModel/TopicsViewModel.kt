package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import kotlinx.coroutines.flow.first
import model.TopicModel
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class TopicsViewModel: BaseViewModel() {
    // Save list topic
    val listTopic = mutableStateListOf<TopicModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    /**
     * Load list topic
     */
    fun loadListTopics() {
        workingWithApiHaveDialog(
            service = {
                firebaseMusicsHelper.loadListTopicOnFB().first()
            },
            progressInBackground = {},
            progressInLayout = {
                listTopic.addAll(it)
            },
            onErrorThrowable = {}
        )
    }
}