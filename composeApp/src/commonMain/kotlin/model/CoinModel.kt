package model

import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class CoinModel {
    var id: String = ""
    var coin: Long = 0
    var money: Long = 0
    var discount: Double = 0.0
    var expireAt: Long? = 0
}

// Dummy coin
fun createDummyCoinsDataList() : MutableList<CoinModel> {
    val listCoin = arrayListOf<CoinModel>()

    for (index in 0 .. 5) {
        val indexedValue = index + 1
        val coinModel = CoinModel()
        coinModel.id = indexedValue.toString()
        coinModel.coin = indexedValue * 1000L
        coinModel.money = indexedValue * 2000L
        if (index == 2 || index == 2) {
            coinModel.discount = 7.0 * indexedValue
            coinModel.expireAt = 1712834218104
        }
        listCoin.add(coinModel)
    }

    return listCoin
}