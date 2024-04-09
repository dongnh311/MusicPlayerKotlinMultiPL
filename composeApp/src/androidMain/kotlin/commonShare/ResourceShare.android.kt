package commonShare

import okio.Path
import okio.Path.Companion.toPath

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 9/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
actual fun String.toOkioPath(): Path? {
    return this.toPath()
}