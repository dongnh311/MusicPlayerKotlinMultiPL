package model

import base.BaseModel
import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class EventModel : BaseModel() {
    var title: String = ""
    var subTitle: String = ""
    var content: String = ""
    var image: String? = ""
    var createAt: Long = 0
    var expireAt: Long? = 0
    var type: Int = 0
}