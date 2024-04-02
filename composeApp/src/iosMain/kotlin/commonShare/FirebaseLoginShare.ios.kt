package commonShare

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class IOsFireBaseAuth: FireBaseAuthControl<Any, Any> {
    override lateinit var googleSignInClient: Any

    override lateinit var resultLauncherGoogle: Any

    override  var onLoginGoogleCallBack: OnLoginGoogleCallBack? = null

    override fun logInWithGoogle() {}

    override suspend fun loadFcmToken(): String {
        return ""
    }
}

actual fun loadFireBaseAuthControl(): FireBaseAuthControl<*, *>  = IOsFireBaseAuth()
