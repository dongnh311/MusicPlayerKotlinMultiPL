package commonShare

class DesktopFireBaseAuthControl<T, U> : FireBaseAuthControl<Any, Any> {
    override lateinit var googleSignInClient: Any
    override lateinit var resultLauncherGoogle: Any

    override var onLoginGoogleCallBack: OnLoginGoogleCallBack? = null

    override fun logInWithGoogle() {

    }

    override suspend fun loadFcmToken(): String {
        return "Desktop"
    }
}

actual fun loadFireBaseAuthControl(): FireBaseAuthControl<*, *> {
    return DesktopFireBaseAuthControl<Any, Any>()
}