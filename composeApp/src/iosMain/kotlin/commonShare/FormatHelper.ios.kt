package commonShare

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

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
    return try {
        val numberObject: NSNumber = NSNumber(number as Double)
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
    return 0
}


/**
 * Make timestamp to view time
 *
 * @return String
 */
actual fun viewTimeByTimestamp(timestamp: Number?, pattern: String): String {
    return ""
}