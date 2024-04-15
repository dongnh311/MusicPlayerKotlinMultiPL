package utils.helper

import const.FB_DATABASE_COINS
import const.FB_DATABASE_PAYMENT_COIN
import const.FB_DATABASE_PAYMENT_HISTORY
import const.FB_DATABASE_VIP
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.BuyCoinModel
import model.CoinModel
import model.FavouriteModel
import model.PaymentModel
import model.VipModel
import model.createListDummyCoins
import model.createListDummyVip

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
        val listCoinModel = createListDummyCoins()
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
            listCoinModel.add(coinModel)
        }
        trySend(listCoinModel)
        awaitClose {
            close()
        }
    }

    /**
     * Write list coin to firebase
     *
     * @return
     */
    suspend fun writeVipToFB() : MutableList<VipModel> {
        val listVips = createListDummyVip()
        listVips.forEach { vip ->
            firebaseStore.collection(FB_DATABASE_VIP).document.set(VipModel.serializer(), vip)
        }

        return listVips
    }

    /**
     * Load list coin
     */
    fun loadListVipFromFB() = callbackFlow {
        val documents = firebaseStore.collection(FB_DATABASE_VIP).get().documents
        val listVip = arrayListOf<VipModel>()
        documents.forEach {documentSnapshot ->
            val vipModel = documentSnapshot.data<VipModel>()
            listVip.add(vipModel)
        }
        trySend(listVip)
        awaitClose {
            close()
        }
    }

    /**
     * Write Purchase coin to firebase
     *
     * @return
     */
    suspend fun writePurchaseCoinsToFB(buyCoinModel: BuyCoinModel)  {
        firebaseStore.collection(FB_DATABASE_PAYMENT_HISTORY).document.set(BuyCoinModel.serializer(), buyCoinModel)
    }

    /**
     * Load list Purchase coin by user
     *
     * @param userId
     */
    fun loadCoinPurchaseOfUser(userId: String) = callbackFlow {
        if (userId.isEmpty()) {
            trySend(arrayListOf())

            awaitClose {
                close()
            }
        }

        val documents = firebaseStore.collection(FB_DATABASE_PAYMENT_HISTORY)
        val listFavourite = mutableListOf<BuyCoinModel>()

        // Query data
        // documents.where { all("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" equalTo "") }
        // documents.where { any("" equalTo "", "" greaterThan "") }
        // documents.where { "userId" greaterThanOrEqualTo userId }

        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            val buyCoinModel = documentSnapshot.data<BuyCoinModel>()
            buyCoinModel.id = documentSnapshot.id
            listFavourite.add(buyCoinModel)
        }
        trySend(listFavourite)
        awaitClose {
            close()
        }
    }

    /**
     * Write payment coin to firebase
     *
     * @return
     */
    suspend fun writePaymentToFB(paymentModel: PaymentModel)  {
        firebaseStore.collection(FB_DATABASE_PAYMENT_COIN).document.set(PaymentModel.serializer(), paymentModel)
    }

    /**
     * Load list payment coin by user
     *
     * @param userId
     */
    fun loadPaymentOfUser(userId: String) = callbackFlow {
        if (userId.isEmpty()) {
            trySend(arrayListOf())

            awaitClose {
                close()
            }
        }

        val documents = firebaseStore.collection(FB_DATABASE_PAYMENT_COIN)
        val listFavourite = mutableListOf<PaymentModel>()

        documents.where { "userId" equalTo userId }.get().documents.forEach {documentSnapshot ->
            val paymentModel = documentSnapshot.data<PaymentModel>()
            paymentModel.id = documentSnapshot.id
            listFavourite.add(paymentModel)
        }
        trySend(listFavourite)
        awaitClose {
            close()
        }
    }
}