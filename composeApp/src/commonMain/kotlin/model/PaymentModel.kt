package model

import const.PAYMENT_TYPE_VIP
import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@Serializable
class PaymentModel {
    var id: String = ""
    var userId: String = ""
    var itemId: String = ""
    var typeOfItem: Int = PAYMENT_TYPE_VIP
    var createAt: Long = 0
    var expireAt: Long = 0
    var bonus = 0
    var status: Int = 0
}