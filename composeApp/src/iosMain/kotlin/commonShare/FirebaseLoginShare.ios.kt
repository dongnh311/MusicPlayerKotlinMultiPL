package commonShare

import co.touchlab.kermit.Logger
import platform.AVFAudio.AVMusicTimeStamp
import platform.Foundation.NSDate
import platform.UserNotifications.UNAuthorizationOptions
import singleton.MusicPlayerSingleton

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

var iosFireBaseAuth = IOsFireBaseAuth()
var iosMusicPlayerSingleTon: MusicPlayerSingleTonIOs? = null

open class IOsFireBaseAuth: FireBaseAuthControl<Any, Any> {
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

/**
 * Public on kotlin common
 *
 * @return
 */
actual fun loadFireBaseAuthControl(): FireBaseAuthControl<*, *> = iosFireBaseAuth

/**
 * Config singleton from native
 *
 * @param musicPlayerSingleTonIOs
 */
fun configSingletonForIos(musicPlayerSingleTonIOs: MusicPlayerSingleTonIOs) {
    iosMusicPlayerSingleTon = musicPlayerSingleTonIOs
}

/**
 * Config form ios native
 *
 * @param input
 */
fun configFirebaseLoginIos(input: IOsFireBaseAuth) {
    iosFireBaseAuth = input
}

/**
 * Interface singleton of ios
 */
interface MusicPlayerSingleTonIOs {
    fun loadTextForTest(): String

    fun loadTimestampOfIOs() : Number

    fun loadNSDateFormIOs(timeStamp: Number) : NSDate
}

