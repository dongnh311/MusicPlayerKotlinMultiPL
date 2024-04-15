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
    var thumbnail: String = ""
}

// Dummy coin
fun createListDummyCoins() : MutableList<CoinModel> {
    val listCoin = arrayListOf<CoinModel>()

    for (index in 0 until 5) {
        val indexedValue = index + 1
        val coinModel = CoinModel()
        coinModel.id = indexedValue.toString()
        coinModel.coin = indexedValue * 1000L
        coinModel.money = indexedValue * 2000L
        if (index == 1 || index == 3) {
            coinModel.discount = 4.3 * indexedValue
            coinModel.expireAt = 1713164089876 / 1000
        }
        listCoin.add(coinModel)
    }

    listCoin[0].thumbnail = "gs://musicplayer-c39cb.appspot.com/common/coin_1.png"
    listCoin[1].thumbnail = "gs://musicplayer-c39cb.appspot.com/common/coin_2.png"
    listCoin[2].thumbnail = "gs://musicplayer-c39cb.appspot.com/common/coin_3.png"
    listCoin[3].thumbnail = "gs://musicplayer-c39cb.appspot.com/common/coin_4.png"
    listCoin[4].thumbnail = "gs://musicplayer-c39cb.appspot.com/common/coin_5.png"

    return listCoin
}