package commonShare

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.darwin.NSInteger

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
actual class DecimalFormat actual constructor() {
    actual fun format(number: Number?, pattern: String): String {
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
}