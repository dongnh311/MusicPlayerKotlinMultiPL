package commonShare

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

interface FireBaseAuthControl<T, R> {
    var googleSignInClient: T
    var resultLauncherGoogle: R
    var onLoginGoogleCallBack: OnLoginGoogleCallBack?

    fun logInWithGoogle()
}

interface OnLoginGoogleCallBack {
    fun onStartLogin()
    fun onLoginComplete(userModel: UserModel)
    fun onLoginFail(exception: Exception)
}
expect fun loadFireBaseAuthControl() : FireBaseAuthControl<*, *>

class UserModel {
    var userName: String = ""
    var profileImage: String = ""
}