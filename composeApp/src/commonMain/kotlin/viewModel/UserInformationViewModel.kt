package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import commonShare.loadFireBaseStorage
import commonShare.loadTimestamp
import const.ACCOUNT_TYPE_FREE
import const.PAYMENT_TYPE_VIP
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import model.CoinModel
import model.ImagePickerModel
import model.PaymentModel
import model.UserModel
import model.VipModel
import utils.helper.FirebaseCoinHelper
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

    // Firebase coin
    private val firebaseCoinHelper = FirebaseCoinHelper()

    // List item to buy vip
    val listVipToBuy = mutableStateListOf<VipModel>()

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

    /**
     * Load list item to buy vip
     */
    fun loadListItemVip() {
        workingWithApiHaveDialog(
            service = {
                firebaseCoinHelper.loadListVipFromFB().first()
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listVipToBuy.clear()
                listVipToBuy.addAll(it)
            }
        )
    }

    /**
     * Buy Vip for user
     *
     * @param userModel
     * @param vipModel
     */
    fun buyVipForUser(userModel: UserModel, vipModel: VipModel) {
        workingWithApiHaveDialog(
            service = {
                val paymentModel = PaymentModel()
                paymentModel.userId = userModel.id
                paymentModel.itemId = vipModel.id
                paymentModel.bonus = vipModel.bonus
                paymentModel.typeOfItem = PAYMENT_TYPE_VIP
                paymentModel.createAt = loadTimestamp().toLong()
                paymentModel.expireAt = loadTimestamp().toLong() + ((vipModel.dayToExpire + vipModel.bonus) * 86400)
                firebaseCoinHelper.writePaymentToFB(paymentModel)
                userModel.coin -= vipModel.coinNeedToPay
                firebaseUser.updateVipForUser(userModel)
                firebaseUser.updateCoinForUser(userModel)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                userModel.accountType = userModel.id
            }
        )
    }
}