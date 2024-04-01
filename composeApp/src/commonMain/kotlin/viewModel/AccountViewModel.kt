package viewModel

import androidx.compose.runtime.mutableStateOf
import base.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import commonShare.OnLoginGoogleCallBack
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import model.UserModel
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

    /**
     * Check and handle user
     *
     * @param userModel
     */
    fun checkInformationUserAndSave(userModel: UserModel, callBack: () -> Unit) {
        this@AccountViewModel.screenModelScope.launch {
            startWorking()
            firebaseUser.checkUserInformationExits(userModel.id).collect {
                if (!it) {
                    firebaseUser.writeUserToFirebaseStore(userModel)
                    stopWorking()
                } else {
                    firebaseUser.loadUserDetailInformation(userModel.id).collect {user ->
                        userDataModel.value = user
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
}