package commonShare

import okio.Path

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 9/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
// ios
import okio.Path.Companion.toPath
actual fun String.toOkioPath(): okio.Path? {
    return toPath()
}
