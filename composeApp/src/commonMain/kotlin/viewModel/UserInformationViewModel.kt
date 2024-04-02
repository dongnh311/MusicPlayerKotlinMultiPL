package viewModel

import androidx.compose.runtime.Composable
import base.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
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
            progressInBackground = {},
            progressInLayout = {
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
            progressInBackground = {},
            progressInLayout = {
                callback.invoke(it)
            },
            onErrorThrowable = {}
        )
    }
}