package viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import base.BaseViewModel
import co.touchlab.kermit.Logger
import const.FB_DATABASE_MUSICS
import const.FB_DATABASE_SINGER
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
    val listSingers = mutableStateListOf<SingerModel>()

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
            var topics = firebaseMusicsHelper.loadListTopicOnFB().first()

            var singers = firebaseMusicsHelper.loadListSingerOnFB().first()

            val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

            var musicsNew = firebaseMusicsHelper.loadListNewMusicsOnFB().first()

            // Save list singer
            if (singers.isEmpty()) {
                singers = firebaseMusicsHelper.writeDataSingerToFB()
            }

            // Convert image url
            loadImageSingerFirebase(singers).first()

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
            val listMusicUpdate = loadImageMusicFirebase(musics).first()

            listMusics.clear()
            listMusics.addAll(listMusicUpdate)

            // Music New
            if (musicsNew.isEmpty()) {
                musicsNew = firebaseMusicsHelper.writeDataMusicNewToFB()
            }

            val listMusicNewLocal = mutableListOf<MusicModel>()
            for (musicId in musicsNew) {
                val find = listMusicUpdate.find { item -> musicId.id == item.id }
                find?.let {
                    listMusicNewLocal.add(find)
                }
            }

            listNewMusics.clear()
            listNewMusics.addAll(listMusicNewLocal)
        }
    }

    /**
     * Find new url image
     *
     * @param musics
     */
    private fun loadImageMusicFirebase(musics : MutableList<MusicModel>) = callbackFlow {
        coroutineScope.launch {
            for (item in musics) {
                if (item.imageUrl.startsWith("gs://")) {
                    // Thumb
                    val newUrlImage =  firebaseMusicsHelper.findUrlLoadImage(item.imageUrl)
                    item.imageUrl = newUrlImage
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_MUSICS, item.id, "imageUrl", newUrlImage)
                }

                if (item.url.startsWith("gs://")) {
                    // File to play
                    val newUrlFile = firebaseMusicsHelper.findUrlLoadImage(item.url)
                    item.url = newUrlFile
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_MUSICS, item.id, "url", newUrlFile)
                }

                // Find singer
                item.singerModel = listSingers.find { singer -> singer.id == item.singerId }
            }
            trySend(musics)
        }

        awaitClose {
            close()
        }
    }

    /**
     * Load avatar for singer
     *
     * @param singers
     */
    private fun loadImageSingerFirebase(singers: MutableList<SingerModel>) = callbackFlow {
        coroutineScope.launch {
            for (item in singers) {
                if (item.avatar.startsWith("gs://")) {
                    // Thumb
                    val newAvatar =  firebaseMusicsHelper.findUrlLoadImage(item.avatar)
                    item.avatar = newAvatar
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_SINGER, item.id, "avatar", newAvatar)
                }
            }
            trySend(singers)
        }

        awaitClose {
            close()
        }
    }
}