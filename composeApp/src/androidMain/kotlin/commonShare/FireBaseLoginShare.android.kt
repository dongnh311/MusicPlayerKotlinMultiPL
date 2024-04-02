package commonShare

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.dongnh.musicplayer.AndroidMusicPlayerSingleton
import com.dongnh.musicplayer.MainActivity
import com.dongnh.musicplayer.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import const.LOGIN_BY_GOOGLE
import const.PLATFORM_ANDROID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import model.UserModel
import timber.log.Timber
import utils.exts.toStringRemoveNull

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 28/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class AndroidFirebaseAuthControl : FireBaseAuthControl<GoogleSignInClient, ActivityResultLauncher<Intent>> {
    // Google signIn
    override lateinit var googleSignInClient: GoogleSignInClient

    // Handel callback form result
    override lateinit var resultLauncherGoogle: ActivityResultLauncher<Intent>

    // Save auth
    private lateinit var auth: FirebaseAuth

    // Callback to view
    override var onLoginGoogleCallBack: OnLoginGoogleCallBack? = null

    /**
     * Init firebase auth
     */
    private fun initAuthFirst() {
        if (!::auth.isInitialized) {
            auth = Firebase.auth
        }
    }

    /**
     * Init config for google signIn
     */
    fun initGoogleSignIn() {
        val composerActivity: MainActivity? = AndroidMusicPlayerSingleton.mainActivity
        initAuthFirst()
        composerActivity?.let {
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(composerActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(composerActivity, gso)

            // Result login by google on request activity
            resultLauncherGoogle =
                composerActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    val data: Intent? = result.data
                    // There are no request codes
                    if (result.resultCode == Activity.RESULT_OK) {
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        try {
                            // Google Sign In was successful, authenticate with Firebase
                            val account = task.getResult(ApiException::class.java)!!
                            Timber.d("firebaseAuthWithGoogle: $account.id")
                            loginUserGoogleToFirebase(composerActivity, account.idToken!!)
                        } catch (e: ApiException) {
                            // Google Sign In failed, update UI appropriately
                            Timber.w("Google sign in failed $e")
                            onLoginGoogleCallBack?.onLoginFail(e)
                        }
                    } else {
                        Timber.e("Can't login with google")
                    }
                }
        }

    }

    /**
     * Step 1, login with google first
     */
    override fun logInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncherGoogle.launch(signInIntent)
    }

    /**
     * Step 2, if login with google complete, please login with firebase to user database
     */
    private fun loginUserGoogleToFirebase(activity: MainActivity, tokenId: String) {
        val credential = GoogleAuthProvider.getCredential(tokenId, null)
        onLoginGoogleCallBack?.onStartLogin()
        initAuthFirst()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("signInWithCredential:success")
                    val user = auth.currentUser
                    val userModel = UserModel()
                    userModel.id = user?.uid.toString()
                    userModel.userName = user?.displayName.toStringRemoveNull()
                    userModel.profileImage = user?.photoUrl?.toString().toStringRemoveNull()
                    userModel.loginType = LOGIN_BY_GOOGLE
                    userModel.platform = PLATFORM_ANDROID
                    onLoginGoogleCallBack?.onLoginComplete(userModel)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.e("signInWithCredential:failure " + task.exception)
                    task.exception?.let { onLoginGoogleCallBack?.onLoginFail(it) }
                }
            }
            .addOnFailureListener {
                Timber.e(it)
            }
    }

    override suspend fun loadFcmToken() : String {
        val job = CoroutineScope(Dispatchers.IO).async {
            val jobResult =
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Timber.e("Fetching FCM registration token failed" + task.exception)
                        return@OnCompleteListener
                    }
                })

            jobResult.await()

            return@async jobResult.result
        }

        return job.await()
    }
}

actual fun loadFireBaseAuthControl(): FireBaseAuthControl<*, *> = AndroidMusicPlayerSingleton.androidFirebaseAuthControl