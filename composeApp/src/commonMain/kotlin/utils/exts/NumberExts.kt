package utils.exts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */


@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Float.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Double.pxToDp() = with(LocalDensity.current) { this@pxToDp.toInt().toDp() }

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

/**
 * Convert String to int
 *
 * @param inputString
 * @return
 */
fun extractInt(inputString: String): Int {
    val num = inputString.replace("\\D".toRegex(), "")
    // return 0 if no digits found
    return if (num.isEmpty()) 0 else num.toInt()
}