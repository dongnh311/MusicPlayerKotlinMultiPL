package const

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 29/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

const val ACCOUNT_TYPE_FREE = "1"
const val ACCOUNT_TYPE_VIP = "2"
const val ACCOUNT_TYPE_SUPPER_VIP = "3"
const val ACCOUNT_TYPE_MAX = "4"

const val PLATFORM_ANDROID = "Android"
const val PLATFORM_DESKTOP = "Desktop"
const val PLATFORM_IOS = "IOs"

const val LOGIN_BY_GOOGLE = "Google"
const val LOGIN_BY_FACEBOOK = "FaceBook"
const val LOGIN_BY_EMAIL = "Email"

val EMAIL_ADDRESS_REGEX = Regex(
"[a-zA-Z0-9+._%\\-]{1,256}" +
"@" +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
"(" +
"\\." +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
")+"
)