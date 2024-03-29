package model

import base.BaseModel
import const.ACCOUNT_TYPE_FREE
import const.PLATFORM_ANDROID
import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class UserModel: BaseModel() {
    var userName: String = ""
    var profileImage: String = ""
    var platform: String = PLATFORM_ANDROID
    var fcmToken: String = ""
    var coin: Double = 0.0
    var accountType: Int = ACCOUNT_TYPE_FREE
}