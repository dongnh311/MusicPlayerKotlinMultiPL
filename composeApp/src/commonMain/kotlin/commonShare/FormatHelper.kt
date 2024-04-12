package commonShare

import const.DATE_LOCAL_FORMAT_TYPE_COMMON_0
import dev.gitlive.firebase.firestore.Timestamp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

/**
 * Format number to money
 *
 * @param number
 * @param pattern
 * @return String
 */
expect fun formatNumberToMoney(number: Number?, pattern: String = "###,###,##0.0"): String

/**
 * Load timestamp local
 *
 * @return
 */
expect fun loadTimestamp(): Number

/**
 * Make timestamp to view time
 *
 * @return String
 */
expect fun viewTimeByTimestamp(timestamp: Number?, pattern: String = DATE_LOCAL_FORMAT_TYPE_COMMON_0): String