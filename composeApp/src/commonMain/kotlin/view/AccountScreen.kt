package view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import childView.LoginField
import childView.PasswordField
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.OnLoginGoogleCallBack
import commonShare.loadFireBaseAuthControl
import kotlinx.coroutines.launch
import model.UserModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_email
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_facebook
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_google
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_logout
import musicplayerkotlinmultipl.composeapp.generated.resources.login_with_email
import musicplayerkotlinmultipl.composeapp.generated.resources.title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import styles.buttonColorsEmail
import styles.buttonColorsFacebook
import styles.buttonColorsGoogle
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.iconSize24dp
import styles.textButton
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent
import utils.helper.FirebaseUserHelper
import viewModel.AccountViewModel
import viewModel.MainViewModel

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

    private val isLogin = mutableStateOf(viewModel.firebaseUser.checkUserLogin())
    private val loginWithAccount = mutableStateOf(false)
    private val emailLogin = mutableStateOf("")
    private val password = mutableStateOf("")
    private val isLogout =  mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val userModel = remember { viewModel.userDataModel }

        if (isLogin.value) {
            // Load user
            if (viewModel.userDataModel.value.id.isEmpty()) {
                viewModel.loadUserInformation(viewModel.firebaseUser.loadUserId())
            }

            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth().padding(16.dp)
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
                                val painter = if (userModel.value.profileImage.isNotEmpty()) rememberImagePainter(userModel.value.profileImage)  else rememberImagePainter("https://..")
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
                                    Text(text = userModel.value.platform, style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                }
                            }
                        }
                    }
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    if (loginWithAccount.value) {
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
                                    // Add another single item
                                    Text(text = stringResource(Res.string.login_with_email), style = textTittleContent(), modifier = Modifier.padding(top = 16.dp))
                                    LoginField(
                                        value = emailLogin.value,
                                        onChange = {
                                            emailLogin.value = it
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    PasswordField(
                                        value = password.value,
                                        onChange = {
                                            password.value = it
                                        },
                                        submit = {
                                            Logger.e("Click login on keyboard")
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Button(onClick = {

                                    }, modifier = Modifier.width(150.dp).padding(bottom = 16.dp)) {
                                        Text("Login")
                                    }
                                }
                            }
                        )
                    } else {
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
                                loginWithAccount.value = true
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
                }
            }
        }

        // Make for logout
        if (isLogout.value) {
            logoutUser()
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
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
                viewModel.checkInformationUserAndSave(userModel)
            }

            override fun onLoginFail(exception: Exception) {
                Logger.e("Google login error", exception)
            }
        }
        firebaseAuth.logInWithGoogle()
    }

    /**
     * Logout user
     *
     */
    @Composable
    private fun logoutUser() {
        showDialogConfirm(
            content = "Are you sure to logout?",
            textButtonLeft = "Cancel",
            textButtonRight = "Logout",
            callBackLeft = {
                isLogout.value = false
            },
            callBackRight = {
                isLogout.value = false
                viewModel.screenModelScope.launch {
                    viewModel.firebaseUser.logoutAuth()
                }
            }
        )

    }
}