package view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.model.screenModelScope
import childView.InputPasswordField
import childView.InputTextField
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.CallBackResultPermission
import commonShare.formatNumberToMoney
import commonShare.loadPermissionControl
import const.ACCOUNT_TYPE_FREE
import const.ACCOUNT_TYPE_MAX
import const.ACCOUNT_TYPE_SUPPER_VIP
import const.ACCOUNT_TYPE_VIP
import const.LOGIN_BY_EMAIL
import const.LOGIN_BY_FACEBOOK
import const.LOGIN_BY_GOOGLE
import const.PERMISSION_GRAND
import const.PLATFORM_ANDROID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.ImagePickerModel
import model.UserModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_buy_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_edit
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_email
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_facebook
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_google
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_android
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_max_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_supper_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_ios
import musicplayerkotlinmultipl.composeapp.generated.resources.pick_image_fail_permission
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_btn_save
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type_free
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_birth_day
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_birth_day_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_login_with
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_login_with_email
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_login_with_facebook
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_login_with_google
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_name
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_name_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_old_password
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_old_password_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_password
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_platform
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_re_password
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_update_pass
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonCircleAvatarColor
import styles.buttonCommonModifier
import styles.buttonSize32dp
import styles.primaryDark
import styles.textContentPrimary
import utils.dialogs.DialogBuyVip
import viewModel.UserInformationViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class UserInformationScreen: BaseScreen<UserInformationViewModel>() {
    var userModel: UserModel = UserModel()

    override var viewModel: UserInformationViewModel = UserInformationViewModel()
    private lateinit var focusManager: FocusManager
    private var keyboardController : SoftwareKeyboardController? = null

    private val userName = mutableStateOf("")
    private val birthDay = mutableStateOf("")
    private val userAvatar = mutableStateOf("")

    private val currentPassword = mutableStateOf("")
    private val newPassword = mutableStateOf("")
    private val newRePassword = mutableStateOf("")

    private val isEnableButtonSave = mutableStateOf(false)

    private val isUpdatePasswordDone = mutableStateOf(false)
    private val isUpdateInformationDone = mutableStateOf(false)

    private val isShowDialogFailPermission = mutableStateOf(false)

    // Open dialog vip
    private val isOpenDialogBuyVip = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun makeContentForView() {
        // Focus
        focusManager = LocalFocusManager.current
        // Keyboard control
        keyboardController = LocalSoftwareKeyboardController.current

        // Save old data
        userName.value = userModel.userName
        birthDay.value = userModel.dayOfBirth
        userAvatar.value = userModel.profileImage

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Icon(
                    painter = painterResource(Res.drawable.btn_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .buttonSize32dp()
                        .clickable {
                            navigator.pop()
                        }.align(Alignment.CenterStart)
                )
                Spacer(modifier = Modifier.height(45.dp))
            }
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1f).padding(all = 16.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                        val painter = if (userAvatar.value.isNotEmpty()) rememberImagePainter(userAvatar.value) else painterResource(Res.drawable.avatar_default)
                        Box(modifier = Modifier) {
                            Image(
                                painter = painter,
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                modifier = Modifier
                                    .size(104.dp)
                                    .clip(CircleShape)                       // clip to the circle shape
                                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                            )

                            // Edit avatar
                            IconButton(
                                onClick = {
                                    Logger.e("Click update avatar")
                                    handleUpdateAvatar()
                                },
                                modifier = Modifier.size(width = 30.dp, height = 30.dp).padding().align(
                                    Alignment.BottomEnd
                                ),
                                content = {
                                    // Specify the icon using the icon parameter
                                    Icon(painter = painterResource(Res.drawable.btn_edit),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Unspecified,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                },
                                colors = buttonCircleAvatarColor()
                            )
                        }

                        // Name
                        InputTextField(
                            value = userName.value,
                            onChange = {newName ->
                                userName.value = newName
                                isEnableButtonSave.value = checkToEnableButtonSave()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = stringResource(Res.string.user_information_name),
                            placeholder = stringResource(Res.string.user_information_name_pl),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }, keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            )
                        )

                        // Birth day
                        InputTextField(
                            value = birthDay.value,
                            onChange = {newDay ->
                                birthDay.value = newDay
                                isEnableButtonSave.value = checkToEnableButtonSave()
                            },
                            modifier = Modifier.fillMaxWidth().padding(top= 8.dp),
                            label = stringResource(Res.string.user_information_birth_day),
                            placeholder = stringResource(Res.string.user_information_birth_day_pl),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.DateRange,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }, keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            ),
                            readOnly = true
                        )

                        // Coin
                        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).border(
                            border = BorderStroke(1.dp, color = primaryDark),
                            shape = RoundedCornerShape(4.dp)
                        ),
                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                            Text(text = stringResource(Res.string.user_information_coin),
                                style = textContentPrimary(), modifier = Modifier.weight(1f).padding(start = 16.dp, top= 16.dp, bottom = 16.dp))

                            Text(text = formatNumberToMoney(userModel.coin),
                                style = textContentPrimary(), modifier = Modifier.padding(end = 8.dp))

                            // Buy coin
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier.size(width = 40.dp, height = 40.dp).padding(end= 16.dp),
                                content = {
                                    // Specify the icon using the icon parameter
                                    Icon(painter = painterResource(Res.drawable.btn_buy_coin),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.Unspecified,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                }
                            )
                        }

                        // Platform
                        if (false) {
                            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).border(
                                border = BorderStroke(1.dp, color = primaryDark),
                                shape = RoundedCornerShape(4.dp)
                            ),
                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                                Text(text = stringResource(Res.string.user_information_platform) ,
                                    style = textContentPrimary(), modifier = Modifier.weight(1f).padding(start = 16.dp, top= 16.dp, bottom = 16.dp))

                                Text(text = userModel.platform,
                                    style = textContentPrimary(), modifier = Modifier.padding(end = 8.dp))

                                val icon = if (userModel.platform == PLATFORM_ANDROID) {
                                    painterResource(Res.drawable.icon_android)
                                } else {
                                    painterResource(Res.drawable.icon_ios)
                                }
                                IconButton(
                                    onClick = {

                                    },
                                    modifier = Modifier.size(width = 40.dp, height = 40.dp).padding(end = 16.dp),
                                    content = {
                                        // Specify the icon using the icon parameter
                                        Icon(painter = icon,
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp),
                                            tint = Color.Unspecified,
                                        )
                                        Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                    }
                                )
                            }
                        }

                        // Type Vip
                        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).clickable {
                            isOpenDialogBuyVip.value = true
                        }.border(
                            border = BorderStroke(1.dp, color = primaryDark),
                            shape = RoundedCornerShape(4.dp)
                        ),
                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                            Text(text = stringResource(Res.string.user_information_account_type),
                                style = textContentPrimary(), modifier = Modifier.weight(1f).padding(start = 16.dp, top= 16.dp, bottom = 16.dp))

                            Text(text = findAccountType(),
                                style = textContentPrimary(), modifier = Modifier.padding(end = 8.dp))

                            val icon = when (userModel.accountType) {
                                ACCOUNT_TYPE_FREE -> {
                                    painterResource(Res.drawable.icon_coin)
                                }
                                ACCOUNT_TYPE_VIP -> {
                                    painterResource(Res.drawable.icon_coin_vip)
                                }
                                ACCOUNT_TYPE_SUPPER_VIP -> {
                                    painterResource(Res.drawable.icon_coin_supper_vip)
                                }
                                ACCOUNT_TYPE_MAX -> {
                                    painterResource(Res.drawable.icon_coin_max_vip)
                                }
                                else -> {
                                    painterResource(Res.drawable.icon_coin_supper_vip)
                                }
                            }
                            IconButton(
                                onClick = {
                                          // Todo
                                    isOpenDialogBuyVip.value = true
                                },
                                modifier = Modifier.size(width = 40.dp, height = 40.dp).padding(end = 16.dp),
                                content = {
                                    // Specify the icon using the icon parameter
                                    Icon(painter = icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.Unspecified,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                }
                            )
                        }

                        // Account
                        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).border(
                            border = BorderStroke(1.dp, color = primaryDark),
                            shape = RoundedCornerShape(4.dp)
                        ),
                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                            Text(text = stringResource(Res.string.user_information_login_with),
                                style = textContentPrimary(), modifier = Modifier.weight(1f).padding(start = 16.dp, top= 16.dp, bottom = 16.dp))

                            val icon = when (userModel.loginType) {
                                LOGIN_BY_GOOGLE -> {
                                    painterResource(Res.drawable.btn_google)
                                }
                                LOGIN_BY_FACEBOOK -> {
                                    painterResource(Res.drawable.btn_facebook)
                                }
                                else -> {
                                    painterResource(Res.drawable.btn_email)
                                }
                            }

                            val typeAccount  = when (userModel.loginType) {
                                LOGIN_BY_GOOGLE -> {
                                    stringResource(Res.string.user_information_login_with_google)
                                }
                                LOGIN_BY_FACEBOOK -> {
                                    stringResource(Res.string.user_information_login_with_facebook)
                                }
                                else -> {
                                    stringResource(Res.string.user_information_login_with_email)
                                }
                            }

                            Text(text = typeAccount,
                                style = textContentPrimary(), modifier = Modifier.padding(end = 8.dp))
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier.size(width = 40.dp, height = 40.dp).padding(end = 16.dp),
                                content = {
                                    // Specify the icon using the icon parameter
                                    Icon(painter = icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.Unspecified,
                                    )
                                    Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                }
                            )
                        }

                        // Only email have password
                        if (userModel.loginType == LOGIN_BY_EMAIL) {
                            // Old Password
                            InputPasswordField(
                                value = currentPassword.value,
                                onChange = {newValue ->
                                    currentPassword.value = newValue
                                    isEnableButtonSave.value = checkToEnableButtonSave()
                                },
                                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Password
                                ),
                                label = stringResource(Res.string.user_information_old_password),
                                placeholder = stringResource(Res.string.user_information_old_password_pl),
                            )

                            // Password
                            InputPasswordField(
                                value = newPassword.value,
                                onChange = {newValue ->
                                    newPassword.value = newValue
                                    isEnableButtonSave.value = checkToEnableButtonSave()
                                },
                                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Password
                                ),
                                label = stringResource(Res.string.user_information_password),
                                placeholder = stringResource(Res.string.user_information_old_password_pl),
                            )

                            // Re-password
                            InputPasswordField(
                                value = newRePassword.value,
                                onChange = {newValue ->
                                    newRePassword.value = newValue
                                    isEnableButtonSave.value = checkToEnableButtonSave()
                                },
                                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                    }
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Password
                                ),
                                label = stringResource(Res.string.user_information_re_password),
                                placeholder = stringResource(Res.string.user_information_old_password_pl),
                            )
                        }
                        Spacer(modifier = Modifier.heightIn(min = 16.dp))
                    }

                    // Button Save
                    Button(onClick = {
                        controlToSaveUserInformation()
                    }, modifier = Modifier.width(200.dp).padding(top = 16.dp, bottom = 16.dp).buttonCommonModifier(), enabled = isEnableButtonSave.value) {
                        Text(stringResource(Res.string.reset_password_btn_save))
                    }

                    Spacer(modifier = Modifier.height(20.dp).padding(bottom = 16.dp))
                }
            }
        })

        // Update password done
        if (isUpdatePasswordDone.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.user_information_update_pass),
                callBackOk = {
                    isUpdatePasswordDone.value = false
                    viewModel.logoutAccount {
                        navigator.pop()
                    }
                }
            )
        }

        // Show dialog need permission
        if (isShowDialogFailPermission.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.pick_image_fail_permission),
                callBackOk = {
                    isShowDialogFailPermission.value = false
                    // TODO : Goto setting
                }
            )
        }

        // Show dialog buy vip
        if (isOpenDialogBuyVip.value) {
            DialogBuyVip(
                isOpenDialogBuyVip,
                viewModel.listVipToBuy,
                sheetState = rememberModalBottomSheetState(),
                onDismiss = {
                    isOpenDialogBuyVip.value = false
                },
                callBackClickBuy = {
                    isOpenDialogBuyVip.value = false
                    viewModel.buyVipForUser(userModel, it)
                }
            )
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listVipToBuy.isEmpty()) {
            viewModel.loadListItemVip()
        }
    }

    override fun onDisposedScreen() {
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun findAccountType() : String {
        return if (userModel.accountType == ACCOUNT_TYPE_FREE) {
            stringResource(Res.string.user_information_account_type_free)
        }
        else {
            stringResource(Res.string.user_information_account_type_vip)
        }
    }


    /**
     * Check to open button save
     *
     * @return
     */
    private fun checkToEnableButtonSave() : Boolean {
        val checkNameAndBirth = userName.value.isNotEmpty() && userName.value != userModel.userName ||
                birthDay.value.isNotEmpty() && birthDay.value != userModel.dayOfBirth || userAvatar.value.isNotEmpty() && userAvatar.value != userModel.profileImage
        return if (currentPassword.value.isEmpty() && newPassword.value.isEmpty() && newRePassword.value.isEmpty()) {
            checkNameAndBirth
        } else {
            currentPassword.value.length >= 6 &&
                    newPassword.value.length >=6 && newRePassword.value.length >= 6 &&
                    newPassword.value == newRePassword.value && checkNameAndBirth
        }
    }

    /**
     * Save user information
     */
    private fun controlToSaveUserInformation() {
        userModel.userName = userName.value
        userModel.dayOfBirth = birthDay.value
        userModel.profileImage = userAvatar.value
        viewModel.updateInformationOfUser(userModel) {
            Logger.e("Update account information complete")
        }

        // Update Password
        if (currentPassword.value.isNotEmpty()) {
            viewModel.changeNewPassword(oldPassword = currentPassword.value, newPassword.value) {
                isUpdatePasswordDone.value = true
            }
        }
    }

    /**
     * Handle to load pick image for avatar
     */
    private fun handleUpdateAvatar() {
        val permissionControl = loadPermissionControl()
        permissionControl.callBackResultPermission = object : CallBackResultPermission {
            override fun onResultPermission(result: Int) {
                if (result == PERMISSION_GRAND) {
                    viewModel.coroutineScope.launch {
                        val listImages = permissionControl.loadAllImageMedia().first()
                        Logger.e("Load image on device : ${listImages.size}")
                    }
                } else {
                    Logger.e("Fail to request permission")
                    isShowDialogFailPermission.value = true
                }
            }
        }

        if (permissionControl.checkPermissionStorage()) {
            viewModel.screenModelScope.launch {
                val listImages = permissionControl.loadAllImageMedia().first()
                Logger.e("Load image on device : ${listImages.size}")
                Logger.e("Load image on device : ${listImages.size}")
                val pickImageScreen = PickImageScreen(listImages)
                navigator.push(pickImageScreen)
                pickImageScreen.onSelectedImageAvatar = object : PickImageScreen.OnSelectedImageAvatar {
                    override fun onSelectedImage(imagePickerModel: ImagePickerModel) {
                        Logger.e("Callback item image selected : ${imagePickerModel.mediaPath}")
                        userAvatar.value = imagePickerModel.mediaPath
                        viewModel.uploadAvatar(this@UserInformationScreen.userModel, imagePickerModel) {
                            viewModel.updateInformationOfUser(this@UserInformationScreen.userModel) {
                                Logger.e("Update new path for avatar to firebase done")
                            }
                        }
                    }
                }
            }
        } else {
            permissionControl.requestPermissionStorage()
            Logger.e("Request permission")
        }
    }

}