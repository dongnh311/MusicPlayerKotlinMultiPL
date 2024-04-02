package viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import base.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import commonShare.OnLoginGoogleCallBack
import commonShare.getPlatform
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import model.UserModel
import utils.exts.toStringRemoveNull
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AccountViewModel: BaseViewModel() {

    var userDataModel = mutableStateOf(UserModel())

    // Firebase data base
    val firebaseUser = FirebaseUserHelper()

    // Token FCM
    var tokenFCM = ""

    /**
     * Check and handle user
     *
     * @param userModel
     */
    fun checkInformationUserAndSave(userModel: UserModel, callBack: () -> Unit) {
        this@AccountViewModel.screenModelScope.launch {
            userModel.fcmToken = tokenFCM
            startWorking()
            firebaseUser.checkUserInformationExits(userModel.id).collect {
                if (!it) {
                    firebaseUser.writeUserToFirebaseStore(userModel)
                    userDataModel.value = userModel
                    stopWorking()
                } else {
                    firebaseUser.updatePlatformAndToken(userModel)
                    firebaseUser.loadUserDetailInformation(userModel.id).collect {user ->
                        userModel.cloneDataFromFirebase(user)
                        userDataModel.value = userModel
                        stopWorking()
                        callBack.invoke()
                    }
                }
            }
        }
    }

    /**
     * Load user information
     *
     * @param userId
     */
    fun loadUserInformation(userId: String) {
        this@AccountViewModel.screenModelScope.launch {
            startWorking()
            firebaseUser.loadUserDetailInformation(userId).catch {
                stopWorking()
                Logger.e("Load user fail", it)
            }.collect {user ->
                userDataModel.value = user
                stopWorking()
            }
        }
    }

    /**
     * Load user information and show update if need
     *
     * @param userId
     */
    fun loadUserInformationAndCheck(userId: String, callBack: (UserModel) -> Unit) {
        this@AccountViewModel.screenModelScope.launch {
            startWorking()
            firebaseUser.loadUserDetailInformation(userId).catch {
                stopWorking()
                Logger.e("Load user fail", it)
            }.collect {user ->
                userDataModel.value = user
                callBack.invoke(user)
                stopWorking()
            }
        }
    }

    /**
     * Create new account
     *
     * @param email
     * @param password
     */
    fun createNewAccountEmail(email: String, password: String, callBack: (UserModel?) -> Unit) {
        workingWithApiHaveDialog<FirebaseUser?>(
            service = {
                firebaseUser.createNewAccountWithEmail(email, password)
            },
            progressInBackground = {},
            progressInLayout = {user ->
                if (user != null) {
                    val userModel = UserModel()
                    userModel.id = user.uid
                    userModel.userName = user.displayName.toStringRemoveNull()
                    userModel.profileImage = user.photoURL.toStringRemoveNull()
                    userModel.platform = getPlatform().name
                    userModel.fcmToken = tokenFCM
                    callBack.invoke(userModel)

                    screenModelScope.launch(dispatchersIO + coroutineExceptionHandler) {
                        firebaseUser.sendVerifiedEmail()
                    }

                } else {
                    callBack.invoke(null)
                }
            },
            onErrorThrowable = {
                callBack.invoke(null)
            }
        )
    }

    /**
     * Login with email
     *
     * @param email
     * @param password
     * @param callBack
     */
    fun loginWithEmailPassword(email: String, password: String, callBack: (String) -> Unit) {
        workingWithApiHaveDialog<String>(
            service = {
                firebaseUser.loginWithEmailPassword(email, password)
            },
            progressInBackground = {},
            progressInLayout = {
                callBack.invoke(it)
            },
            onErrorThrowable = {

            }
        )
    }

    /**
     * Save new password
     *
     * @param code
     * @param password
     * @param callBack
     */
    fun resetPasswordWithCode(code: String, password: String, callBack: () -> Unit) {
        workingWithApiHaveDialog(
            service = {
                firebaseUser.resetPasswordForEmail(code, password)
            },
            progressInBackground = {},
            progressInLayout = {
                callBack.invoke()
            },
            onErrorThrowable = {}
        )
    }
}