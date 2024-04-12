package model

import base.BaseModel
import const.ACCOUNT_TYPE_FREE
import const.LOGIN_BY_GOOGLE
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
    var email: String = ""
    var dayOfBirth: String = ""
    var platform: String = PLATFORM_ANDROID
    var fcmToken: String = ""
    var coin: Double = 0.0
    var accountType: Int = ACCOUNT_TYPE_FREE
    var loginType: String = LOGIN_BY_GOOGLE
    var expireVip: Long? = 0

    /**
     * Clone data
     *
     * @param userModel
     */
    fun cloneDataFromFirebase(userModel: UserModel) {
        this.userName = userModel.userName
        this.profileImage = userModel.profileImage
        this.coin = userModel.coin
        this.accountType = userModel.accountType
        this.expireVip = userModel.expireVip
    }
}