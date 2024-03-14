package base

import kotlinx.serialization.Serializable
import model.ErrorModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
abstract class BaseResponseModel<T> {
    var data: T? = null

    var result: String? = null

    var error: ErrorModel? = null
}