package model

import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class BuyCoinModel {
    var id : String = ""
    var userId: String = ""
    var coinId: String = ""
    var createAt: Long = 0
}