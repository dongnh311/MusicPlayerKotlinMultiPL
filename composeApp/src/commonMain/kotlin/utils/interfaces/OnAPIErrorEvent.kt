package utils.interfaces

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
interface OnAPIErrorEvent {
    fun onErrorThrow(error: Throwable)
    fun onErrorString(error: String)
    fun onErrorInt(error: Int)
}