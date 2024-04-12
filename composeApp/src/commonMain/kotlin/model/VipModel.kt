package model

import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class VipModel {
    var id: String = ""
    var name: String = ""
    var coinNeedToPay: Long = 0
    var dayToExpire: Double = 0.0
    var bonus: Int = 0
}

// Dummy for vip
fun createListDummyVip() : MutableList<VipModel> {
    val listVipModel = arrayListOf<VipModel>()

    for (index in 0 .. 7) {
        val indexedValue = index + 1
        val vipModel = VipModel()
        vipModel.id = indexedValue.toString()
        vipModel.name = ""
        vipModel.coinNeedToPay = 100L * indexedValue
        vipModel.dayToExpire = indexedValue * 1.0
        vipModel.bonus = 0
    }

    return listVipModel
}