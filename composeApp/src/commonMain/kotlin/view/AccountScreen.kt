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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.BaseScreen
import cafe.adriel.voyager.core.model.screenModelScope
import childView.InputPasswordField
import childView.InputTextField
import childView.LoginField
import childView.LoginLogoApp
import childView.PasswordField
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.OnLoginGoogleCallBack
import commonShare.formatNumberToMoney
import commonShare.getPlatform
import commonShare.loadFireBaseAuthControl
import const.ACCOUNT_TYPE_FREE
import const.ACCOUNT_TYPE_VIP
import const.LOGIN_BY_EMAIL
import const.LOGIN_BY_FACEBOOK
import const.LOGIN_BY_GOOGLE
import kotlinx.coroutines.launch
import model.UserModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.account_about
import musicplayerkotlinmultipl.composeapp.generated.resources.account_albums
import musicplayerkotlinmultipl.composeapp.generated.resources.account_coin_history
import musicplayerkotlinmultipl.composeapp.generated.resources.account_my_favourite
import musicplayerkotlinmultipl.composeapp.generated.resources.account_play_history
import musicplayerkotlinmultipl.composeapp.generated.resources.account_play_list
import musicplayerkotlinmultipl.composeapp.generated.resources.account_settings
import musicplayerkotlinmultipl.composeapp.generated.resources.account_version
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_about
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_album
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_email
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_facebook
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_active
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_unactive
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_google
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_logout
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_my_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_play_history
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_setting
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_version
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_btn
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_fail
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_name
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_name_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_password
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_password_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_re_password
import musicplayerkotlinmultipl.composeapp.generated.resources.create_new_account_title
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_btn
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_content
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_done
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_email
import musicplayerkotlinmultipl.composeapp.generated.resources.forgot_password_email_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_supper_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.login_with_email
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_btn_save
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_code
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_code_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_password
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_password_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_re_password
import musicplayerkotlinmultipl.composeapp.generated.resources.reset_password_title
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_account_type
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_error_information
import musicplayerkotlinmultipl.composeapp.generated.resources.user_information_login_with
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonColorAccount
import styles.buttonColorsEmail
import styles.buttonColorsFacebook
import styles.buttonColorsGoogle
import styles.colorAccountCard
import styles.colorPrimaryApp
import styles.iconSize30dp
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.paddingTop8
import styles.paddingTop8StartEnd16
import styles.textButton
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent
import utils.exts.checkEmailValidate
import viewModel.AccountViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AccountScreen : BaseScreen<AccountViewModel>(){

    override var viewModel: AccountViewModel = AccountViewModel()

    // Load firebase login
    private val firebaseAuth = loadFireBaseAuthControl()

    private val isShowButtonBack = mutableStateOf(false)

    private val isLogin = mutableStateOf(viewModel.requestCheckUserLogin())
    private val isOpenDialogMakeUpdate = mutableStateOf(viewModel.isUserNeedUpdateInformation)
    private val isLogout =  mutableStateOf(false)

    private val emailLogin = mutableStateOf("")
    private val password = mutableStateOf("")
    private val isEnableButtonLogin = mutableStateOf(false)

    private val newAccountEmail = mutableStateOf("")
    private val newAccountPassword = mutableStateOf("")
    private val newAccountRePassword = mutableStateOf("")
    private val isEnableButtonCreate = mutableStateOf(false)

    private val emailToReset = mutableStateOf("")
    private val isEnableButtonReset = mutableStateOf(false)

    private val resetCodeEmail = mutableStateOf("")
    private val resetPassword = mutableStateOf("")
    private val resetRePassword = mutableStateOf("")
    private val isEnableSavePassword = mutableStateOf(false)
    private val isSavePasswordDone = mutableStateOf(false)

    private val isLoginWithAccount = mutableStateOf(false)
    private val isCreateNewAccount =  mutableStateOf(false)
    private val isForgotAccount = mutableStateOf(false)
    private val isResetEmailDone = mutableStateOf(false)
    private val isShowResetPassword = mutableStateOf(false)

    private val isErrorCreateAccount = mutableStateOf(false)

    private lateinit var focusManager: FocusManager
    private var keyboardController : SoftwareKeyboardController? = null

    // Screen
    private val favouritesScreen by lazy { FavouritesScreen() }
    private val playlistScreen by lazy { PlaylistScreen() }
    private val playHistoryScreen by lazy { PlayHistoryScreen() }
    private val userInformationScreen by lazy { UserInformationScreen() }
    private val aboutScreen by lazy { AboutScreen() }
    private val coinsScreen by lazy { CoinsScreen() }
    private val settingScreen by lazy { SettingScreen() }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val userModel = remember { viewModel.userDataModel }
        // Focus
        focusManager = LocalFocusManager.current
        // Keyboard control
        keyboardController = LocalSoftwareKeyboardController.current

        if (isLogin.value) {
            // Load user
            if (viewModel.userDataModel.value.id.isEmpty()) {
                viewModel.loadUserInformation(viewModel.firebaseUser.loadUserId())
            }

            showViewAccount(userModel)
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (isShowButtonBack.value) {
                    IconButton(
                        onClick = {
                            resetAllViewToDefault()
                        },
                        modifier = Modifier.size(width = 40.dp, height = 40.dp).align(Alignment.TopStart),
                        content = {
                            // Specify the icon using the icon parameter
                            Icon(painter = painterResource(Res.drawable.btn_back),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = Color.Unspecified,
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                        }
                    )
                }
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isLoginWithAccount.value) { // Login by email
                        showViewLoginByEmail()
                        isShowButtonBack.value = true
                    } else if (isCreateNewAccount.value) {  // Create new account
                        showViewCreateAccount()
                        isShowButtonBack.value = true
                    } else if (isForgotAccount.value) { // Forgot
                        showViewForgotPassword()
                        isShowButtonBack.value = true
                    } else if (isShowResetPassword.value) {
                        showViewSaveNewPassword()
                        isShowButtonBack.value = true
                    } else {
                        // Login with SNN
                        showViewLoginViaSNS()
                    }
                }
            }
        }

        // Make for logout
        if (isLogout.value) {
            logoutUser()
        }

        // Make for reset
        if (isResetEmailDone.value) {
            showMessageEmailResetDone()
        }

        // Make error create account
        if (isErrorCreateAccount.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.create_new_account_fail)
            ) {
                isErrorCreateAccount.value = false
            }
        }

        // Update password
        if (isSavePasswordDone.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.forgot_password_done)
            ) {
                isSavePasswordDone.value = false
                resetAllViewToDefault()
            }
        }
    }

    override fun onStartedScreen() {
        if (viewModel.tokenFCM.isEmpty()) {
            // Load fcm
            viewModel.screenModelScope.launch {
                viewModel.tokenFCM = firebaseAuth.loadFcmToken()
                Logger.e("FCM Token : ${viewModel.tokenFCM}")
            }
        }
    }

    override fun onDisposedScreen() {
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showViewLoginViaSNS() {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                makeLoginWithGoogle()
            }, modifier = Modifier.width(250.dp), shape = RoundedCornerShape(35.dp), colors = buttonColorsGoogle()) {
                Icon(
                    painter = painterResource(Res.drawable.btn_google),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "drawable icons",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Google",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = textButton(),
                    modifier = Modifier
                        .weight(1f)
                        .offset(x= (-20).dp / 2) //default icon width = 24.dp
                )
            }

            Button(onClick = {

            }, modifier = Modifier.width(250.dp), shape = RoundedCornerShape(35.dp), colors = buttonColorsFacebook()) {
                Icon(
                    painter = painterResource(Res.drawable.btn_facebook),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "drawable icons",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Facebook",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = textButton(),
                    modifier = Modifier
                        .weight(1f)
                        .offset(x= (-20).dp / 2) //default icon width = 24.dp

                )
            }

            Button(onClick = {
                isLoginWithAccount.value = true
                isCreateNewAccount.value = false
            }, modifier = Modifier.width(250.dp), shape = RoundedCornerShape(35.dp), colors = buttonColorsEmail()) {
                Icon(
                    painter = painterResource(Res.drawable.btn_email),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "drawable icons",
                    tint = Color.Unspecified
                )
                Text(
                    text = "Email",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = textButton(),
                    modifier = Modifier
                        .weight(1f)
                        .offset(x= (-20).dp / 2) //default icon width = 24.dp
                )
            }
        }
    }

    /**
     * Logout user
     *
     */
    @Composable
    private fun logoutUser() {
        showDialogConfirm(
            title = "Logout",
            content = "Are you sure to logout?",
            textButtonLeft = "Cancel",
            textButtonRight = "Logout",
            callBackLeft = {
                isLogout.value = false
            },
            callBackRight = {
                isLogout.value = false
                viewModel.screenModelScope.launch {
                    viewModel.firebaseUser.logoutAuth {
                        isLogin.value = false
                    }
                }
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun showViewLoginByEmail() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation =  10.dp,
            ),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    // Logo
                    LoginLogoApp()

                    // Content
                    Text(text = stringResource(Res.string.login_with_email), style = textTittleContent(), modifier = Modifier.padding(top = 16.dp))
                    LoginField(
                        value = emailLogin.value,
                        onChange = {
                            emailLogin.value = it
                            isEnableButtonLogin.value = checkDataToLogin()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    PasswordField(
                        value = password.value,
                        onChange = {
                            password.value = it
                            isEnableButtonLogin.value = checkDataToLogin()
                        },
                        submit = {
                            Logger.e("Click login on keyboard")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Button login
                    Button(onClick = {
                        viewModel.loginWithEmailPassword(emailLogin.value, password.value) {
                            if (it.isNotEmpty()) {
                                viewModel.loadUserInformationAndCheck(it) {user ->
                                    user.loginType = LOGIN_BY_EMAIL
                                    user.email = emailLogin.value
                                    if (user.userName.isEmpty()) {
                                        userInformationScreen.userModel = user
                                        navigator.push(userInformationScreen)
                                    } else {
                                        isLogin.value = true
                                    }
                                }
                            }
                        }
                    }, modifier = Modifier.width(200.dp).padding(bottom = 0.dp), enabled = isEnableButtonLogin.value) {
                        Text("Login")
                    }

                    // Forgot
                    Row(modifier = Modifier.fillMaxWidth()) {
                        // Create account
                        Text(
                            modifier = Modifier.drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Blue,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            }.clickable(true) {
                                Logger.e("Create account")
                                isLoginWithAccount.value = false
                                isCreateNewAccount.value = true
                                isForgotAccount.value = false

                            }, color = Color.Blue,
                            text = stringResource(Res.string.create_new_account),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.drawBehind {
                                val strokeWidthPx = 1.dp.toPx()
                                val verticalOffset = size.height - 2.sp.toPx()
                                drawLine(
                                    color = Color.Blue,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            }.clickable(true) {
                                isLoginWithAccount.value = false
                                isCreateNewAccount.value = false
                                isForgotAccount.value = true
                            }, color = Color.Blue, textAlign = TextAlign.End,
                            text = "Forgot password?",
                        )
                    }

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun showViewCreateAccount() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation =  10.dp,
            ),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    // Logo
                    LoginLogoApp()

                    // Add another single item
                    Text(text = stringResource(Res.string.create_new_account_title), style = textTittleContent(), modifier = Modifier.padding(top = 16.dp))
                    LoginField(
                        value = newAccountEmail.value,
                        onChange = {
                            newAccountEmail.value = it
                            isEnableButtonCreate.value = checkDataCreateIsCorrect()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(Res.string.create_new_account_name),
                        placeholder = stringResource(Res.string.create_new_account_name_pl)
                    )

                    // Password
                    InputPasswordField(
                        value = newAccountPassword.value,
                        onChange = {
                            newAccountPassword.value = it
                            isEnableButtonCreate.value = checkDataCreateIsCorrect()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Password
                        ),
                        label = stringResource(Res.string.create_new_account_password),
                        placeholder = stringResource(Res.string.create_new_account_password_pl),
                    )

                    // Re-password
                    InputPasswordField(
                        value = newAccountRePassword.value,
                        onChange = {
                            newAccountRePassword.value = it
                            isEnableButtonCreate.value = checkDataCreateIsCorrect()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        ),
                        label = stringResource(Res.string.create_new_account_re_password),
                        placeholder = stringResource(Res.string.create_new_account_password_pl),
                    )

                    // Button Create
                    Button(onClick = {
                        viewModel.createNewAccountEmail(newAccountEmail.value, newAccountPassword.value) {
                            handleUpdateUserInformation(it)
                        }
                    }, modifier = Modifier.width(200.dp).padding(bottom = 0.dp), enabled = isEnableButtonCreate.value) {
                        Text(stringResource(Res.string.create_new_account_btn))
                    }

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showViewAccount(userModel: MutableState<UserModel>) {
        if (isOpenDialogMakeUpdate.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.user_information_error_information),
                callBackOk = {
                    userInformationScreen.userModel = userModel.value
                    navigator.push(userInformationScreen)
                }
            )
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Column (modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top) {
                    // User info
                    Card(
                        modifier = Modifier
                            .fillMaxWidth().padding(16.dp).clickable(enabled = true) {
                                userInformationScreen.userModel = userModel.value
                                navigator.push(userInformationScreen)
                            }
                        ,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation =  10.dp,
                        ),
                        shape = RoundedCornerShape(
                            topEnd = 5.dp,
                            topStart = 5.dp,
                            bottomEnd = 5.dp,
                            bottomStart = 5.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorAccountCard
                        ),
                        content = {
                            Box(modifier = Modifier.padding(16.dp)) {
                                IconButton(
                                    onClick = {
                                        isLogout.value = true
                                    },
                                    modifier = Modifier.size(width = 45.dp, height = 45.dp).align(Alignment.TopEnd),
                                    content = {
                                        // Specify the icon using the icon parameter
                                        Icon(painter = painterResource(Res.drawable.btn_logout),
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp),
                                        )
                                        Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                    }
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp)
                                ) {
                                    val painter = if (userModel.value.profileImage.isNotEmpty()) rememberImagePainter(userModel.value.profileImage) else painterResource(Res.drawable.avatar_default)
                                    Image(
                                        painter = painter,
                                        contentDescription = "avatar",
                                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .size(84.dp)
                                            .clip(CircleShape)                       // clip to the circle shape
                                            .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                                    )
                                    // Add another single item
                                    Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
                                        Text(text = userModel.value.userName, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                                        Text(text = "Coin : " + formatNumberToMoney(userModel.value.coin), style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                        Row(modifier = Modifier.fillMaxWidth().paddingTop16()) {
                                            val icon = when (userModel.value.loginType) {
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
                                            Icon(
                                                painter = icon,
                                                modifier = iconSize30dp(),
                                                contentDescription = stringResource(Res.string.user_information_login_with),
                                                tint = Color.Unspecified
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))

                                            val iconAccount = when (userModel.value.accountType) {
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
                                            Icon(
                                                painter = iconAccount,
                                                modifier = iconSize30dp(),
                                                contentDescription = stringResource(Res.string.user_information_account_type),
                                                tint = Color.Unspecified
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    )

                    // Button play history
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop16StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            navigator.push(playHistoryScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_play_history),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_play_history),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_play_history), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    // Button play list
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            navigator.push(playlistScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_playlist),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_play_list),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_play_list), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    if (false) {
                        // Button Album
                        androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                            border = BorderStroke(1.dp, colorPrimaryApp),
                            shape = RoundedCornerShape(5),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 1.dp,
                                pressedElevation = 2.dp,
                                disabledElevation = 2.dp,
                            ),
                            colors = buttonColorAccount(),
                            onClick = {

                            }) {
                            Row(horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)){
                                Icon(
                                    painter = painterResource(Res.drawable.btn_album),
                                    modifier = iconSize30dp(),
                                    contentDescription = stringResource(Res.string.account_albums),
                                    tint = Color.Unspecified
                                )

                                Text(text = stringResource(Res.string.account_albums), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }

                    // Button Fav
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            navigator.push(favouritesScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_fav_unactive),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_my_favourite),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_my_favourite), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    // Button Coin
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            coinsScreen.userModel = userModel.value
                            navigator.push(coinsScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_my_coin),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_coin_history),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_coin_history), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    // Button Setting
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            navigator.push(settingScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_setting),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_settings),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_settings), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }


                    // Button About
                    androidx.compose.material3.Button(modifier = Modifier.paddingTop8StartEnd16(),
                        border = BorderStroke(1.dp, colorPrimaryApp),
                        shape = RoundedCornerShape(5),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 2.dp,
                        ),
                        colors = buttonColorAccount(),
                        onClick = {
                            navigator.push(aboutScreen)
                        }) {
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)){
                            Icon(
                                painter = painterResource(Res.drawable.btn_about),
                                modifier = iconSize30dp(),
                                contentDescription = stringResource(Res.string.account_about),
                                tint = Color.Unspecified
                            )

                            Text(text = stringResource(Res.string.account_about), style = textButton(), modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                }
                // Version
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp).align(Alignment.BottomCenter)){
                    Icon(
                        painter = painterResource(Res.drawable.btn_version),
                        modifier = iconSize30dp(),
                        contentDescription = stringResource(Res.string.account_version),
                        tint = Color.Unspecified,
                    )

                    Text(text = stringResource(Res.string.account_version) + " ${getPlatform().versionApp}", style = textButton(), modifier = Modifier.padding(start = 8.dp))
                }
            }

            //Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top) {}
        }
    }

    /**
     * View for verify and save new password
     *
     */
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showViewSaveNewPassword() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation =  10.dp,
            ),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    // Logo
                    LoginLogoApp()

                    // Add another single item
                    Text(text = stringResource(Res.string.reset_password_title), style = textTittleContent(), modifier = Modifier.padding(top = 16.dp))
                    LoginField(
                        value = resetCodeEmail.value,
                        onChange = {
                            resetCodeEmail.value = it
                            isEnableSavePassword.value = checkDataSaveNewPassword()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(Res.string.reset_password_code),
                        placeholder = stringResource(Res.string.reset_password_code_pl)
                    )

                    // Password
                    InputPasswordField(
                        value = resetPassword.value,
                        onChange = {
                            resetPassword.value = it
                            isEnableSavePassword.value = checkDataSaveNewPassword()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Password
                        ),
                        label = stringResource(Res.string.reset_password_password),
                        placeholder = stringResource(Res.string.reset_password_password_pl),
                    )

                    // Re-password
                    InputPasswordField(
                        value = newAccountRePassword.value,
                        onChange = {
                            newAccountRePassword.value = it
                            isEnableButtonCreate.value = checkDataCreateIsCorrect()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        ),
                        label = stringResource(Res.string.reset_password_re_password),
                        placeholder = stringResource(Res.string.reset_password_password_pl),
                    )

                    // Button Save
                    Button(onClick = {
                        viewModel.resetPasswordWithCode(resetCodeEmail.value, resetPassword.value) {
                            isSavePasswordDone.value = true
                        }
                    }, modifier = Modifier.width(200.dp).padding(bottom = 0.dp), enabled = isEnableButtonCreate.value) {
                        Text(stringResource(Res.string.reset_password_btn_save))
                    }

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showViewForgotPassword() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation =  10.dp,
            ),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    // Logo
                    LoginLogoApp()

                    // Add another single item
                    Text(text = stringResource(Res.string.forgot_password), style = textTittleContent(), modifier = Modifier.padding(top = 16.dp))
                    InputTextField(
                        value = emailToReset.value,
                        onChange = {
                            emailToReset.value = it
                            isEnableButtonReset.value = checkDataResetIsCorrect()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = stringResource(Res.string.forgot_password_email),
                        placeholder = stringResource(Res.string.forgot_password_email_pl),
                        leadingIcon = {
                            androidx.compose.material3.Icon(
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

                    // Button reset
                    Button(onClick = {
                        Logger.e("Reset password")
                        isResetEmailDone.value = true
                    }, modifier = Modifier.width(200.dp).padding(bottom = 0.dp),
                        enabled = isEnableButtonReset.value) {
                        Text(stringResource(Res.string.forgot_password_btn))
                    }

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        )
    }

    /**
     * Make dialog show done reset
     *
     */
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showMessageEmailResetDone() {
        showDialogMessage(title = "", content = stringResource(Res.string.forgot_password_content)) {
            isResetEmailDone.value = false
            isLoginWithAccount.value = false
            isCreateNewAccount.value = false
            isForgotAccount.value = false
            isShowButtonBack.value = false
            isShowResetPassword.value = true
        }
    }

    /**
     * Handle login by google
     */
    private fun makeLoginWithGoogle() {
        firebaseAuth.onLoginGoogleCallBack = object : OnLoginGoogleCallBack {
            override fun onStartLogin() {
                Logger.e("Google Start login")
            }

            override fun onLoginComplete(userModel: UserModel) {
                Logger.e("Google login done")
                viewModel.checkInformationUserAndSave(userModel) {
                    isLogin.value = true
                }
            }

            override fun onLoginFail(exception: Exception) {
                Logger.e("Google login error", exception)
            }
        }

        firebaseAuth.logInWithGoogle()
    }

    /**
     * Check value email to reset is correct
     *
     * @return
     */
    private fun checkDataResetIsCorrect() : Boolean {
        return emailToReset.value.isNotEmpty() && emailToReset.value.checkEmailValidate()
    }

    /**
     * Check value input on create new account
     *
     * @return
     */
    private fun checkDataCreateIsCorrect() : Boolean {
        return newAccountPassword.value.isNotEmpty() && newAccountRePassword.value.isNotEmpty() &&
                newAccountPassword.value.length >= 6 && newAccountRePassword.value.length >= 6 &&
                newAccountPassword.value == newAccountRePassword.value && newAccountEmail.value.isNotEmpty() &&
                newAccountEmail.value.checkEmailValidate()
    }

    /**
     * Check data is validate for update new password
     *
     * @return
     */
    private fun checkDataSaveNewPassword() : Boolean {
        return resetPassword.value.isNotEmpty() && resetRePassword.value.isNotEmpty() &&
                resetPassword.value.length >= 6 && resetRePassword.value.length >= 6 &&
                resetPassword.value == resetRePassword.value && resetCodeEmail.value.isNotEmpty()
    }

    /**
     * Check information login is validate
     *
     * @return
     */
    private fun checkDataToLogin(): Boolean {
        return emailLogin.value.isNotEmpty() && emailLogin.value.checkEmailValidate() && password.value.isNotEmpty() && password.value.length >= 6
    }

    /**
     * Handle update user
     *
     * @param userModel
     */
    private fun handleUpdateUserInformation(userModel: UserModel?) {
        if (userModel != null) {
            userInformationScreen.userModel = userModel
            navigator.push(userInformationScreen)
        } else {
            isErrorCreateAccount.value = true
        }
    }

    /**
     * Reset all view to default
     */
    private fun resetAllViewToDefault() {
        isLoginWithAccount.value = false
        isCreateNewAccount.value = false
        isForgotAccount.value = false
        isShowResetPassword.value = false
        isShowButtonBack.value = false
    }
}

