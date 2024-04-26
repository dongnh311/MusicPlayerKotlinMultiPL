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

/**
 * Make duration to view time
 *
 * @param inputNumber
 * @return
 */
fun makeDurationToViewString(inputNumber: Long) : String {
    val convertToSecond = inputNumber/ 1000
    
    var minute = convertToSecond / 60
    val second = convertToSecond - (minute * 60)
    var hour = 0L
    if (minute >= 60) {
        hour = (minute / 60)
        minute -= (hour * 60)
    }

    return if (hour != 0L) {
        "${checkIfLessThanTen(hour)}:${checkIfLessThanTen(minute)}:${checkIfLessThanTen(second)}"
    } else {
        "${checkIfLessThanTen(minute)}:${checkIfLessThanTen(second)}"
    }
}

fun checkIfLessThanTen(number: Long) : String {
    if (number < 10) {
        return "0$number"
    } else {
        return number.toString()
    }
}