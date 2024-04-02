package commonShare

import co.touchlab.kermit.Logger
import platform.UserNotifications.UNAuthorizationOptions
import singleton.MusicPlayerSingleton

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

val iosFireBaseAuth = IOsFireBaseAuth()
var iosMusicPlayerSingleTon: MusicPlayerSingleTonIOs? = null

class IOsFireBaseAuth: FireBaseAuthControl<Any, Any> {
    override lateinit var googleSignInClient: Any

    override lateinit var resultLauncherGoogle: Any

    override var onLoginGoogleCallBack: OnLoginGoogleCallBack? = null

    var fcmToken = ""

    override fun logInWithGoogle() {}

    override suspend fun loadFcmToken(): String {
        val text = iosMusicPlayerSingleTon?.loadTextForTest()
        text?.let {
            Logger.e("Text ${it}")
        }
        return fcmToken
    }
}

actual fun loadFireBaseAuthControl(): FireBaseAuthControl<*, *>  = iosFireBaseAuth

fun configSingletonForIos(musicPlayerSingleTonIOs: MusicPlayerSingleTonIOs) {
    iosMusicPlayerSingleTon = musicPlayerSingleTonIOs
}

interface MusicPlayerSingleTonIOs {
    fun loadTextForTest(): String
}

