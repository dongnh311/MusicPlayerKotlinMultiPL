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
    var dayToExpire: Long = 0
    var bonus: Int = 0
}

// Dummy for vip
fun createListDummyVip() : MutableList<VipModel> {
    val listVipModel = arrayListOf<VipModel>()

    var countForMoney = 1
    for (index in 0 until 4) {
        val indexedValue = index + 1
        val vipModel = VipModel()
        vipModel.id = indexedValue.toString()
        vipModel.name = ""
        vipModel.coinNeedToPay = 1000L * indexedValue * countForMoney
        vipModel.dayToExpire = 0
        vipModel.bonus = 0
        listVipModel.add(vipModel)
        countForMoney += 3
    }

    listVipModel[0].name = "Vip 1 Day"
    listVipModel[0].dayToExpire = 1
    listVipModel[1].name = "Vip 1 Week"
    listVipModel[1].dayToExpire = 7
    listVipModel[2].name = "Vip 1 Month"
    listVipModel[2].bonus = 1
    listVipModel[2].dayToExpire = 30
    listVipModel[3].name = "Vip 1 Year"
    listVipModel[3].dayToExpire = 365

    return listVipModel
}