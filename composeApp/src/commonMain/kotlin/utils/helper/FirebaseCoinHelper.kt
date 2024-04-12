package utils.helper

import const.FB_DATABASE_COINS
import const.FB_DATABASE_HISTORY
import const.FB_DATABASE_PLAY_LIST
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.CoinModel
import model.PlayHistoryModel
import model.PlayListModel
import model.createDummyCoinsDataList

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FirebaseCoinHelper {

    // Firebase store
    private val firebaseStore = Firebase.firestore

    /**
     * Write list coin to firebase
     *
     * @return
     */
    suspend fun writeCoinsToFB() : MutableList<CoinModel> {
        val listCoinModel = createDummyCoinsDataList()
        listCoinModel.forEach { coin ->
            firebaseStore.collection(FB_DATABASE_COINS).document.set(CoinModel.serializer(), coin)
        }

        return listCoinModel
    }

    /**
     * Load list coin
     */
    fun loadListCoinFromFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_COINS).get().documents
        val listCoinModel = arrayListOf<CoinModel>()
        documents.forEach {documentSnapshot ->
            val coinModel = documentSnapshot.data<CoinModel>()
            coinModel.id = documentSnapshot.id
            listCoinModel.add(coinModel)
        }
        trySend(listCoinModel)
        awaitClose {
            close()
        }
    }

}