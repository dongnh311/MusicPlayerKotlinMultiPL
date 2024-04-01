package commonShare

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
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
        val numberObject: NSNumber
        when (number) {
            is Int -> {
                numberObject = NSNumber(number)
            }
            is Double -> {
                numberObject = NSNumber(number)
            }
            is Long -> {
                numberObject = NSNumber(number)
            }
            is Float -> {
                numberObject = NSNumber(number)
            }
            else -> {
                numberObject = NSNumber(0)
            }
        }
        return formatter.stringFromNumber(numberObject)!!
    }
}