package model

import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class PlayListModel {
    var userId: String = ""
    var id: String = ""
    var name: String = ""
    var thumbnail: String = ""
    var listMusicsId = arrayListOf<String>()
    var createAt: Long = 0
    var updateAt: Long? = 0

    @Transient
    var isChoose = mutableStateOf(false)
}