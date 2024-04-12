package viewModel

import base.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import commonShare.loadFireBaseStorage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import model.ImagePickerModel
import model.UserModel
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class UserInformationViewModel: BaseViewModel() {

    // Firebase data base
    private val firebaseUser = FirebaseUserHelper()

    // Load firebase storage
    private val firebaseStore = loadFireBaseStorage()

    /**
     * Logout
     *
     * @param callback
     */
    fun logoutAccount(callback: () -> Unit) {
        screenModelScope.launch {
            firebaseUser.logoutAuth { callback.invoke() }
        }
    }

    /**
     * Update user
     *
     * @param userModel
     * @param callback
     */
    fun updateInformationOfUser(userModel: UserModel, callback: () -> Unit) {
        workingWithApiHaveDialog(
            service = {
                firebaseUser.updateInformationUser(userModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                callback.invoke()
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Update new password
     *
     * @param oldPassword
     * @param newPassword
     * @param callback
     */
    fun changeNewPassword(oldPassword: String, newPassword: String, callback: (Boolean) -> Unit) {
        workingWithApiHaveDialog<Boolean>(
            service = {
                firebaseUser.updateNewPassword(oldPassword, newPassword)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                callback.invoke(it)
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Update avatar for user
     *
     * @param userModel
     * @param imagePickerModel
     */
    fun uploadAvatar(userModel: UserModel, imagePickerModel: ImagePickerModel, callback: () -> Unit) {
        workingWithApiHaveDialog(
            service = {
                val task = firebaseStore.uploadAvatar(userModel, imagePickerModel).shareIn(
                    scope = this@UserInformationViewModel.coroutineScope,
                    replay = 1,
                    started = SharingStarted.WhileSubscribed()
                )
                task.first()
            },
            doOnBeforeService = {},
            doOnAfterService = {
                callback.invoke()
            },
            onErrorThrowable = { e ->
                e.printStackTrace()
                Logger.e("Fail to upload avatar", e)
                stopWorking()
            }
        )

    }
}