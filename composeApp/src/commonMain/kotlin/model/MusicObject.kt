package model

import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class MusicObject {
    var id = ""
    var name = ""
    var author = ""
    var duration: Double = 0.0
    var url = ""
    var imageUrl = ""
    var singer = ""
}