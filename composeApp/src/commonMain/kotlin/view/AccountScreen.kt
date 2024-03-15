package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import childView.LoginField
import childView.PasswordField
import co.touchlab.kermit.Logger
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_email
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_facebook
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_google
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
import styles.colorPrimaryBackground
import styles.textButton
import styles.textTittleContent
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

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val isLogin = remember { mutableStateOf(false) }
        val loginWithAccount = remember { mutableStateOf(false) }
        val emailLogin = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        if (isLogin.value) {

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
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}