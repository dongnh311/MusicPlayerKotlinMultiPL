package commonShare

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
actual class DecimalFormat actual constructor() {
    actual fun format(number: Number?, pattern: String): String {
        if (number == 0.0 || number == null) return "0"
        val output = this
        val format = DecimalFormat(pattern)
        val dfs = DecimalFormatSymbols()
        dfs.decimalSeparator = '.'
        dfs.groupingSeparator = ','
        format.decimalFormatSymbols = dfs
        return format.format(output)
    }
}