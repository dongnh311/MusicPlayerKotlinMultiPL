package viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import base.BaseViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.EventModel
import model.MusicModel
import model.SingerModel
import model.TopicModel
import network.response.NotifyResponse
import network.services.EventServices
import singleton.NetworkManager
import singleton.NetworkManager.jsonManager
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class HomeViewModel: BaseViewModel() {

    private val eventServices: EventServices = NetworkManager.apiNetRest.ktorfit.create()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save list new music
    val listNewMusics = mutableStateListOf<MusicModel>()

    // Save list singers
    private val listSingers = mutableStateListOf<SingerModel>()

    // Save list singers
    private val listMusics = mutableStateListOf<MusicModel>()

    // Save list topic
    val listTopic = mutableStateListOf<TopicModel>()

    /**
     * Load list event
     */
    fun loadEvents() = callbackFlow {
        workingWithApiHaveDialog(
            service = {
                eventServices.loadListNotify("https://raw.githubusercontent.com/dongnh311/mockup-api/main/notify.json")
            },
            progressInBackground = {},
            progressInLayout = {
                               Logger.e("Data : ${it.body()}")
                if (it.isSuccessful) {
                    try {
                        val responseBody = it.body()
                        var listDataReturn: MutableList<EventModel>? = mutableListOf()
                        responseBody?.let { data ->
                            listDataReturn = jsonManager.decodeFromString<NotifyResponse>(
                                data
                            ).data
                        }
                        trySend(listDataReturn)
                    } catch (e: Exception) {
                        Logger.e("loadListNotify", e)
                    }
                }
            },
            onErrorThrowable ={}
        )
        awaitClose {
            close()
        }
    }

    /**
     * Load list data
     */
    fun loadListMusicAndSinger() {
        coroutineScope.launch {
            val taskTopic = firebaseMusicsHelper.loadListTopicOnFB()
            var topics = taskTopic.first()

            val taskSingers = firebaseMusicsHelper.loadListSingerOnFB()
            var singers = taskSingers.first()

            val taskMusics = firebaseMusicsHelper.loadListMusicsOnFB()
            val musics = taskMusics.first()

            val taskMusicsNew = firebaseMusicsHelper.loadListNewMusicsOnFB()
            val musicsNew = taskMusics.first()

            // Save list singer
            if (singers.isEmpty()) {
                singers = firebaseMusicsHelper.writeDataSingerToFB()
            }

            listSingers.addAll(singers.toMutableStateList())

            // Sav topic
            if (topics.isEmpty()) {
                topics = firebaseMusicsHelper.writeTopicToFB()
            }

            listTopic.addAll(topics.toMutableStateList())

            // Music
            if (musics.isEmpty()) {
                firebaseMusicsHelper.writeDataMusicToFB()
            }

            // Load new image path
            val listMusicUpdate = loadImageStorageFirebase(musics).first()

            listMusics.clear()
            listMusics.addAll(listMusicUpdate)

            // Music New
            if (musicsNew.isEmpty()) {
                firebaseMusicsHelper.writeDataMusicNewToFB()
            }

            // Load new image path
            val listMusicNewUpdate = loadImageStorageFirebase(musics).first()

            listNewMusics.clear()
            listNewMusics.addAll(listMusicNewUpdate)
        }
    }

    /**
     * Find new url image
     *
     * @param musics
     */
    private fun loadImageStorageFirebase(musics : MutableList<MusicModel>) = callbackFlow{
        coroutineScope.launch {
            for (item in musics) {
                // Thumb
                val newUrlImage =  firebaseMusicsHelper.findUrlLoadImage(item.imageUrl)
                item.imageUrl = newUrlImage

                // File to play
                val newUrlFile = firebaseMusicsHelper.findUrlLoadImage(item.url)
                item.url = newUrlFile

                // Find singer
                item.singerModel = listSingers.find { singer -> singer.id == item.singerId }
            }
            trySend(musics)
        }

        awaitClose {
            close()
        }
    }
}