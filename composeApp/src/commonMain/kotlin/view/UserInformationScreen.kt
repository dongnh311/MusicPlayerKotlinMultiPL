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
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import cafe.adriel.voyager.navigator.tab.CurrentTab
import childView.InputPasswordField
import childView.InputTextField
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.CallBackResultPermission
import commonShare.DecimalFormat
import commonShare.loadPermissionControl
import const.ACCOUNT_TYPE_FREE
import const.ACCOUNT_TYPE_VIP
import const.PERMISSION_GRAND
import const.PLATFORM_ANDROID
import model.UserModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_buy_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_edit
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_email
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_email_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_android
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_supper_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_ios
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_btn_save
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_password
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_password_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_re_password
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type_free
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_brith_day
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_brith_day_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_coin
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
import styles.buttonCommonModifier
import styles.colorPrimaryText
import styles.primaryDark
import styles.textContentPrimary
import styles.textContentSecond
import viewModel.UserInformationViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class UserInformationScreen(private val userModel: UserModel): BaseScreen<UserInformationViewModel>() {

    override var viewModel: UserInformationViewModel = UserInformationViewModel()
    private lateinit var focusManager: FocusManager
    private var keyboardController : SoftwareKeyboardController? = null

    private val userName = mutableStateOf("")
    private val brithDay = mutableStateOf("")
    private val userAvatar = mutableStateOf("")

    private val currentPassword = mutableStateOf("")
    private val newPassword = mutableStateOf("")
    private val newRePassword = mutableStateOf("")

    private val isEnableButtonSave = mutableStateOf(false)

    private val isUpdatePasswordDone = mutableStateOf(false)
    private val isUpdateInformationDone = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        // Focus
        focusManager = LocalFocusManager.current
        // Keyboard control
        keyboardController = LocalSoftwareKeyboardController.current

        // Save old data
        userName.value = userModel.userName
        brithDay.value = userModel.dayOfBrith
        userAvatar.value = userModel.profileImage

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            Icon(
                painter = painterResource(Res.drawable.btn_back),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp).size(40.dp)
                    .clickable {
                        navigator.pop()
                    }
            )
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(all = 16.dp).verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
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
                            modifier = Modifier.size(width = 40.dp, height = 40.dp).padding().align(
                                Alignment.BottomEnd
                            ),
                            content = {
                                // Specify the icon using the icon parameter
                                Icon(painter = painterResource(Res.drawable.btn_edit),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color.Unspecified,
                                )
                                Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                            }
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

                    // Brith day
                    InputTextField(
                        value = brithDay.value,
                        onChange = {newDay ->
                            brithDay.value = newDay
                            isEnableButtonSave.value = checkToEnableButtonSave()
                        },
                        modifier = Modifier.fillMaxWidth().padding(top= 8.dp),
                        label = stringResource(Res.string.user_information_brith_day),
                        placeholder = stringResource(Res.string.user_information_brith_day_pl),
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

                        Text(text = DecimalFormat().format(userModel.coin),
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

                    // Platform
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp).border(
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
                            else -> {
                                painterResource(Res.drawable.icon_coin_supper_vip)
                            }
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

                    Spacer(modifier = Modifier.heightIn(min = 50.dp).weight(1f).padding(bottom = 16.dp))

                    // Button Save
                    Button(onClick = {
                        controlToSaveUserInformation()
                    }, modifier = Modifier.width(150.dp).padding(top = 16.dp, bottom = 16.dp).buttonCommonModifier(), enabled = isEnableButtonSave.value) {
                        Text(stringResource(Res.string.reset_password_btn_save))
                    }

                    Spacer(modifier = Modifier.height(10.dp).weight(1f).padding(bottom = 16.dp))
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
    }

    override fun onStartedScreen() {
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
        val checkNameAndBrith = userName.value.isNotEmpty() && userName.value != userModel.userName ||
                brithDay.value.isNotEmpty() && brithDay.value != userModel.dayOfBrith || userAvatar.value.isNotEmpty() && userAvatar.value != userModel.profileImage
        return if (currentPassword.value.isEmpty() && newPassword.value.isEmpty() && newRePassword.value.isEmpty()) {
            checkNameAndBrith
        } else {
            currentPassword.value.length >= 6 &&
                    newPassword.value.length >=6 && newRePassword.value.length >= 6 &&
                    newPassword.value == newRePassword.value && checkNameAndBrith
        }
    }

    /**
     * Save user information
     */
    private fun controlToSaveUserInformation() {
        userModel.userName = userName.value
        userModel.dayOfBrith = brithDay.value
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
                    val listImages = permissionControl.loadAllImageMedia()
                    Logger.e("Load image on device : ${listImages.size}")
                } else {

                }
            }
        }

        if (permissionControl.checkPermissionStorage()) {
            val listImages = permissionControl.loadAllImageMedia()
            Logger.e("Load image on device : ${listImages.size}")
        } else {
            permissionControl.requestPermissionStorage()
            Logger.e("Request permission")
        }
    }

}