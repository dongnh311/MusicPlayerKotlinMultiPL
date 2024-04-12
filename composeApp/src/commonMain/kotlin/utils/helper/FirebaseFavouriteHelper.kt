package utils.helper

import const.FB_DATABASE_FAVOURITE
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.FavouriteModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebaseFavouriteHelper {
    // Firebase store
    private val firebaseStore = Firebase.firestore

    /**
     * Write favourite to firebase
     *
     * @return
     */
    suspend fun writeFavouriteToFB(favouriteModel: FavouriteModel)  {
        firebaseStore.collection(FB_DATABASE_FAVOURITE).document.set(FavouriteModel.serializer(), favouriteModel)
    }

    /**
     * Load list favourite of user
     *
     * @param userId
     */
    fun loadListFavouriteFromFB(userId: String) = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_FAVOURITE)
        val listFavourite = mutableListOf<FavouriteModel>()

        // Query data
        // documents.where { all("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" greaterThan "") }
        // documents.where { "userId" greaterThanOrEqualTo userId }

        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            val favouriteModel = documentSnapshot.data<FavouriteModel>()
            favouriteModel.id = documentSnapshot.id
            listFavourite.add(favouriteModel)
        }
        trySend(listFavourite)
        awaitClose {
            close()
        }
    }
}