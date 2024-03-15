package network.response

import base.BaseResponseModel
import kotlinx.serialization.Serializable
import model.EventModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class NotifyResponse : BaseResponseModel<MutableList<EventModel>>() {
    override var data: MutableList<EventModel>? = null
}