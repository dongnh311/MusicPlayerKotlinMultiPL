package model

import androidx.compose.runtime.mutableStateOf
import const.FAVOURITE_MUSIC
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@Serializable
class FavouriteModel {
    var id : String = ""
    var userId: String = ""
    var favouriteItemId: String = ""
    var favType: Int = FAVOURITE_MUSIC
    var createAt: Long = 0

    @Transient
    var musicItem: MusicModel? = MusicModel()
}