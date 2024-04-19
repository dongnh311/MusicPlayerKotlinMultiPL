package commonShare

import co.touchlab.kermit.Logger
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_0
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_1
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_2
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_3
import platform.Foundation.NSCoder
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.timeIntervalSince1970
import platform.darwin.NSInteger

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
actual fun formatNumberToMoney(number: Number?, pattern: String): String {
    val formatter = NSNumberFormatter()
    formatter.minimumFractionDigits = 0u
    formatter.maximumFractionDigits = 2u
    formatter.numberStyle = 1u // Decimal
    formatter.groupingSeparator = ","
    formatter.decimalSeparator = "."
    formatter.setPositiveFormat(pattern)
    if (number == null) return "0"
    return try {
        val numberObject = when (number) {
            is Double -> {
                NSNumber(number.toDouble())
            }

            is Int -> {
                NSNumber(number.toInt())
            }

            else -> {
                NSNumber(number.toDouble())
            }
        }
        formatter.stringFromNumber(numberObject)!!

    } catch (e: Exception) {
        e.printStackTrace()
        "0"
    }
}

/**
 * Load timestamp local
 *
 * @return
 */
actual fun loadTimestamp(): Number {
    val local = NSDate().timeIntervalSince1970
    val timestamp = iosMusicPlayerSingleTon?.loadTimestampOfIOs()
    Logger.e("loadTimestamp : $local, native: $timestamp")
    return local
}


/**
 * Make timestamp to view time
 *
 * @return String
 */
actual fun viewTimeByTimestamp(timestamp: Number?, pattern: String): String {
    var dateReturn = ""
    var isNeedStop = false
    var patternInput = pattern
    if (timestamp == null || timestamp == 0L) {
        return "N/A"
    }
    do {
        try {
            var nativeDate = iosMusicPlayerSingleTon?.loadNSDateFormIOs(timestamp)
            if (nativeDate == null) {
                nativeDate = NSDate(timeIntervalSinceReferenceDate = (timestamp.toDouble()))
            }

            val dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = patternInput

           // dateFormatter.timeZone = NSTimeZone.new()!!

            dateFormatter.locale = NSLocale("US")
            Logger.e("NSDate : ${nativeDate}, timestamp : ${timestamp.toDouble()}")

            val convertedDate = dateFormatter.stringFromDate(nativeDate)
            dateReturn = convertedDate
            isNeedStop = true
        } catch (e: Exception) {
            when (patternInput) {
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

            Logger.e("Fail convert, try to new pattern : $patternInput error : ${e.message}")
        }
    } while (!isNeedStop)
    return dateReturn
}