package utils.helper

import const.FB_DATABASE_HISTORY
import const.FB_DATABASE_PLAY_LIST
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Filter
import dev.gitlive.firebase.firestore.Query
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.MusicNewModel
import model.PlayHistoryModel
import model.PlayListModel
import model.TopicModel
import model.createListDummyTopicModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebasePlayHelper {
    // Firebase store
    private val firebaseStore = Firebase.firestore

    /**
     * Load list play history
     *
     * @param userId
     */
    fun loadPlayHistoryList(userId: String) = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_HISTORY)
        val listMusics = mutableListOf<PlayHistoryModel>()

        // Query data
        // documents.where { all("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" greaterThan "") }
        // documents.where { "userId" greaterThanOrEqualTo userId }

        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            val playHistoryModel = documentSnapshot.data<PlayHistoryModel>()
            playHistoryModel.id = documentSnapshot.id
            listMusics.add(playHistoryModel)
        }
        trySend(listMusics)
        awaitClose {
            close()
        }
    }

    /**
     * Delete all history play list
     *
     * @param userId
     */
    suspend fun deleteAllPlayHistory(userId: String) {
        val documents = firebaseStore.collection(FB_DATABASE_HISTORY)
        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            documentSnapshot.reference.delete()
        }
    }

    /**
     * Delete one play history
     *
     * @param historyId
     */
    suspend fun deletePlayHistory(historyId: String)  {
        firebaseStore.collection(FB_DATABASE_HISTORY).document(historyId).delete()
    }

    /**
     * Write History to fb
     *
     * @return
     */
    suspend fun writePlayHistoryToFB(playHistoryModel: PlayHistoryModel)  {
        firebaseStore.collection(FB_DATABASE_HISTORY).document.set(PlayHistoryModel.serializer(), playHistoryModel)
    }

    /**
     * Write playlist to fb
     *
     * @param playListModel
     */
    suspend fun writeNewPlaylistToFB(playListModel: PlayListModel) {
        firebaseStore.collection(FB_DATABASE_PLAY_LIST).document.set(PlayListModel.serializer(), playListModel)
    }

    /**
     * Load list play history
     *
     * @param userId
     */
    fun loadPlayListOfUser(userId: String) = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_PLAY_LIST)
        val listPlaylist = mutableListOf<PlayListModel>()

        // Query data
        // documents.where { all("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" greaterThan "") }
        // documents.where { "userId" greaterThanOrEqualTo userId }

        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            val playHistoryModel = documentSnapshot.data<PlayListModel>()
            playHistoryModel.id = documentSnapshot.id
            listPlaylist.add(playHistoryModel)
        }
        trySend(listPlaylist)
        awaitClose {
            close()
        }
    }

    /**
     * Delete one play history
     *
     * @param playlistId
     */
    suspend fun deletePlayList(playlistId: String)  {
        firebaseStore.collection(FB_DATABASE_PLAY_LIST).document(playlistId).delete()
    }

    /**
     * Update information for playlist
     *
     * @param playListModel
     */
    suspend fun updateInformationPlaylist(playListModel: PlayListModel)  {
        firebaseStore.collection(FB_DATABASE_PLAY_LIST).document(playListModel.id).update(Pair("updateAt", playListModel.updateAt))
        firebaseStore.collection(FB_DATABASE_PLAY_LIST).document(playListModel.id).update(Pair("listMusicsId", playListModel.listMusicsId))
        firebaseStore.collection(FB_DATABASE_PLAY_LIST).document(playListModel.id).update(Pair("name", playListModel.name))
    }
}