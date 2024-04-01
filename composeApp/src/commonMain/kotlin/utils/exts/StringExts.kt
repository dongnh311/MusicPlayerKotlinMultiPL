package utils.exts

import const.EMAIL_ADDRESS_REGEX

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
/**
 * Check string is null and remove "null" to return
 *
 * @return String
 */
fun String?.toStringRemoveNull(): String {
    return this.toString().replace("null", "")
}

/**
 * Check email is validate
 *
 * @return
 */
fun String?.checkEmailValidate() :Boolean{
    return this?.matches(EMAIL_ADDRESS_REGEX) == true
}