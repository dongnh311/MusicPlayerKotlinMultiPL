package singleton

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object MusicPlayerSingleton {
    // Check user login
    val isLogin = MutableStateFlow(false)
}