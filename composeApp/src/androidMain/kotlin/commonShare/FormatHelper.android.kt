package commonShare

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_0
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_1
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_2
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_3
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
actual fun formatNumberToMoney(number: Number?, pattern: String): String {
    if (number == 0.0 || number == null) return "0"
    val output = ""
    val format = DecimalFormat(pattern)
    val dfs = DecimalFormatSymbols()
    dfs.decimalSeparator = '.'
    dfs.groupingSeparator = ','
    format.decimalFormatSymbols = dfs
    return format.format(output)
}

/**
 * Load timestamp local
 *
 * @return
 */
actual fun loadTimestamp(): Number {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    return calendar.timeInMillis / 1000
}



/**
 * Make timestamp to view time
 *
 * @return String
 */
actual fun viewTimeByTimestamp(timestamp: Number?, pattern: String): String {
    var dateReturn: String = ""
    var isNeedStop = false
    var patternInput = pattern
    if (timestamp == null || timestamp == 0L) {
        return "N/A"
    }
    do {
        try {
            val sdf = SimpleDateFormat(patternInput, Locale.US)
            val date = Date(timestamp as Long * 1000)
            dateReturn = sdf.format(date)
            isNeedStop = true
        } catch (e: Exception) {
            when (pattern) {
                DATE_LOCAL_FORMAT_TYPE_COMMON_0 -> {
                    patternInput = DATE_LOCAL_FORMAT_TYPE_COMMON_1
                }

                DATE_LOCAL_FORMAT_TYPE_COMMON_1 -> {
                    patternInput = DATE_LOCAL_FORMAT_TYPE_COMMON_2
                }

                DATE_LOCAL_FORMAT_TYPE_COMMON_2 -> {
                    patternInput = DATE_LOCAL_FORMAT_TYPE_COMMON_3
                }

                DATE_LOCAL_FORMAT_TYPE_COMMON_3 -> {
                    // Last pattern, please end this
                    isNeedStop = true
                }
            }
            Timber.e("Fail convert, try to new pattern : $pattern")
        }
    } while (!isNeedStop)
    return dateReturn
}