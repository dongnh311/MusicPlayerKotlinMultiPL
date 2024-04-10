package utils.helper

import commonShare.loadFireBaseStorage
import const.FB_DATABASE_MUSICS
import const.FB_DATABASE_NEW
import const.FB_DATABASE_SINGER
import const.FB_DATABASE_TOPIC
import const.FIREBASE_STORAGE_MUSICS
import const.FIREBASE_STORAGE_SINGER
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import model.MusicModel
import model.SingerModel
import model.TopicModel
import model.UserModel
import model.createListDummyMusics
import model.createListDummySinger
import model.createListDummyTopicModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 9/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebaseMusicsHelper {
    // Firebase store
    private val firebaseStore = Firebase.firestore

    private val firebaseNative = loadFireBaseStorage()

    /**
     * Write data musics to firebase
     */
    suspend fun writeDataMusicToFB() {
        val listMusic = createListDummyMusics()
        listMusic.forEach {
            firebaseStore.collection(FB_DATABASE_MUSICS).document(it.id).set(MusicModel.serializer(), it)
        }
    }

    /**
     * Write data musics to firebase
     */
    suspend fun writeDataMusicNewToFB() {
        val listMusic = createListDummyMusics()
        listMusic.forEach {
            firebaseStore.collection(FB_DATABASE_NEW).document(it.id).set(MusicModel.serializer(), it)
        }
    }

    /**
     * Write data singers to firebase
     */
    suspend fun writeDataSingerToFB() : MutableList<SingerModel> {
        val listSinger = createListDummySinger()
        listSinger.forEach {
            firebaseStore.collection(FB_DATABASE_SINGER).document(it.id).set(SingerModel.serializer(), it)
        }
        return listSinger
    }

    /**
     * Write topic to fb
     *
     * @return
     */
    suspend fun writeTopicToFB() : MutableList<TopicModel> {
        val listTopicModel =  createListDummyTopicModel()
        listTopicModel.forEach {
            firebaseStore.collection(FB_DATABASE_TOPIC).document(it.id).set(TopicModel.serializer(), it)
        }
        return listTopicModel
    }

    /**
     * Load list new Musics on firebase
     */
    fun loadListMusicsOnFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_MUSICS).get()
        val listMusics = mutableListOf<MusicModel>()
        documents.documents.forEach {documentSnapshot ->
            val musicModel = documentSnapshot.data<MusicModel>()
            musicModel.id = documentSnapshot.id
            listMusics.add(musicModel)
        }
        trySend(listMusics)
        awaitClose {
            close()
        }
    }

    /**
     * Load list new Musics on firebase
     */
    fun loadListNewMusicsOnFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_NEW).get()
        val listMusics = mutableListOf<MusicModel>()
        documents.documents.forEach {documentSnapshot ->
            val musicModel = documentSnapshot.data<MusicModel>()
            musicModel.id = documentSnapshot.id
            listMusics.add(musicModel)
        }
        trySend(listMusics)
        awaitClose {
            close()
        }
    }

    /**
     * Load list singers on firebase
     */
    fun loadListSingerOnFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_SINGER).get()
        val listMusics = mutableListOf<SingerModel>()
        documents.documents.forEach {documentSnapshot ->
            val singerModel = documentSnapshot.data<SingerModel>()
            singerModel.id = documentSnapshot.id
            listMusics.add(singerModel)
        }
        trySend(listMusics)
        awaitClose {
            close()
        }
    }

    /**
     * Load list topic on firebase
     */
    fun loadListTopicOnFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_TOPIC).get()
        val listTopics = mutableListOf<TopicModel>()
        documents.documents.forEach {documentSnapshot ->
            val topicModel = documentSnapshot.data<TopicModel>()
            topicModel.id = documentSnapshot.id
            listTopics.add(topicModel)
        }
        trySend(listTopics)
        awaitClose {
            close()
        }
    }

    /**
     * Load image firebase path
     *
     * @param path
     * @return
     */
    suspend fun findUrlLoadImage(path: String): String {
        val taskRunning = firebaseNative.loadUrlFileStorage(path)
        return taskRunning.first()
    }
}