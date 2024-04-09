package model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 02/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class ImagePickerModel {
    // Id of item, for check in all adapter select or view
    var mediaId: String = ""

    var mediaName: String = ""

    var mediaPath: String = ""

    var mediaType: String = ""

    var isChoose: MutableState<Boolean> = mutableStateOf(false)
}