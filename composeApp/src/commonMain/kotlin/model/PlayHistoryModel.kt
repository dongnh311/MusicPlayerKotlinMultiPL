package model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class PlayHistoryModel {
    var id: String = ""
    var userId: String = ""
    var musicId: String = ""
    var timePlayed: Long = 0

    @Transient
    var musicModel: MusicModel? = null
}