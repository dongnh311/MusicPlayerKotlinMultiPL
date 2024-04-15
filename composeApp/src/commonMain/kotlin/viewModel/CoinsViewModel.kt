package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import commonShare.loadTimestamp
import kotlinx.coroutines.flow.first
import model.BuyCoinModel
import model.CoinModel
import model.PaymentModel
import model.UserModel
import model.VipModel
import utils.exts.extractInt
import utils.helper.FirebaseCoinHelper
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class CoinsViewModel: BaseViewModel() {

    // Firebase coin
    private val firebaseCoinHelper = FirebaseCoinHelper()

    // User helper
    private val firebaseUserHelper = FirebaseUserHelper()

    // List Coin
    val listCoinsToBuy = mutableStateListOf<CoinModel>()

    // List buy coin history
    val listCoinBuyHistory = mutableStateListOf<BuyCoinModel>()

    // List used coin
    val listCoinUsedHistory = mutableStateListOf<PaymentModel>()

    // Save list Vip
    val listItemVip = mutableStateListOf<VipModel>()

    /**
     * Load all data con buy and payment
     */
    fun loadListBuyCoinHistory() {
        workingWithApiHaveDialog(
            service = {
                val listCoin = firebaseCoinHelper.loadListCoinFromFB().first()
                listCoinsToBuy.clear()
                listCoin.sortedWith(compareBy({ item -> extractInt(item.id) }, { v -> extractInt(v.id) }))
                listCoinsToBuy.addAll(listCoin)

                // Coin buy
                val listCoins= firebaseCoinHelper.loadCoinPurchaseOfUser(firebaseUserHelper.loadUserId()).first()
                listCoins.forEach { item ->
                    item.coinModel = listCoinsToBuy.find { coin -> coin.id == item.coinId }
                }
                listCoinBuyHistory.clear()
                listCoinBuyHistory.addAll(listCoins)
            },
            doOnBeforeService = {},
            doOnAfterService = {}
        )
    }

    /**
     * Load list coin used
     */
    fun loadListCoinUsed() {
        workingWithApiHaveDialog(
            service = {
                val listVips = firebaseCoinHelper.loadListVipFromFB().first()
                listItemVip.clear()
                listItemVip.addAll(listVips)

                val listPayments = firebaseCoinHelper.loadPaymentOfUser(firebaseUserHelper.loadUserId()).first()
                listCoinUsedHistory.clear()
                listCoinUsedHistory.addAll(listPayments)
            },
            doOnBeforeService = {},
            doOnAfterService = {}
        )
    }

    /**
     * Buy Coin for user
     *
     * @param userModel
     */
    fun buyCoinForUser(userModel: UserModel, coinModel: CoinModel) {
        workingWithApiHaveDialog(
            service = {
                val buyCoinModel = BuyCoinModel()
                buyCoinModel.userId = userModel.id
                buyCoinModel.coinId = coinModel.id
                buyCoinModel.discount = coinModel.discount
                buyCoinModel.createAt = loadTimestamp().toLong()
                userModel.coin += coinModel.coin

                firebaseCoinHelper.writePurchaseCoinsToFB(buyCoinModel)
                firebaseUserHelper.updateCoinForUser(userModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                loadListBuyCoinHistory()
            },
        )
    }
}