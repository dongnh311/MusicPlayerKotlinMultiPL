package const

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 29/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

val ACCOUNT_TYPE_FREE = 1
val ACCOUNT_TYPE_VIP = 2

val PLATFORM_ANDROID = "Android"
val PLATFORM_IOS = "IOs"

val EMAIL_ADDRESS_REGEX = Regex(
"[a-zA-Z0-9+._%\\-]{1,256}" +
"@" +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
"(" +
"\\." +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
")+"
)