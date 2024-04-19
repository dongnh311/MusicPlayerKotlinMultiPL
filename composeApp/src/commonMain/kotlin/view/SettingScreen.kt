package view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import base.BaseScreen
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_dark_auto
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_dark_mode
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_dark_mode_dark
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_dark_mode_light
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_language
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_language_en
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_language_vn
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_play_background
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_play_background_off
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_play_background_on
import musicplayerkotlinmultipl.composeapp.generated.resources.setting_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonColorAccount
import styles.buttonSize32dp
import styles.colorPrimaryApp
import styles.colorPrimaryBackground
import styles.paddingStart16
import styles.paddingTop16StartEnd16
import styles.paddingTop8StartEnd16
import styles.textButton
import styles.textContentSecond
import styles.textTittleHome
import viewModel.SettingViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SettingScreen: BaseScreen<SettingViewModel>() {
    override var viewModel: SettingViewModel = SettingViewModel()

    private val stateLanguageEnglish = mutableStateOf(false)
    private val stateDarkModeDark = mutableStateOf(true)
    private val autoPlay = mutableStateOf(false)
    private val playOnBackground = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        Scaffold(modifier = Modifier.background(Color.Red).fillMaxSize(), backgroundColor = colorPrimaryBackground,
            topBar = {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(Res.drawable.btn_back),
                        contentDescription = "Back",
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.setting_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
            Column(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()),
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                // Language
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
                        Text(text = stringResource(Res.string.setting_language), style = textButton(), modifier = Modifier.padding(start = 8.dp).weight(1f))

                        val textLanguage = if (stateLanguageEnglish.value) stringResource(Res.string.setting_language_vn)
                        else stringResource(Res.string.setting_language_en)
                        Text(text = textLanguage, style = textContentSecond(), modifier = Modifier.padding(start = 8.dp))
                        Switch(
                            modifier = Modifier.paddingStart16(),
                            checked = stateLanguageEnglish.value,
                            onCheckedChange = { value ->
                                stateLanguageEnglish.value = value
                            }
                        )
                    }
                }

                // Dark mode
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
                    }) {
                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)){

                        Text(text = stringResource(Res.string.setting_dark_mode), style = textButton(), modifier = Modifier.padding(start = 8.dp).weight(1f))

                        val textLanguage = if (stateDarkModeDark.value) stringResource(Res.string.setting_dark_mode_light)
                        else stringResource(Res.string.setting_dark_mode_dark)
                        Text(text = textLanguage, style = textContentSecond(), modifier = Modifier.padding(start = 8.dp))
                        Switch(
                            modifier = Modifier.paddingStart16(),
                            checked = stateDarkModeDark.value,
                            onCheckedChange = { value ->
                                stateDarkModeDark.value = value
                            }
                        )
                    }
                }

                // Auto play
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
                    }) {
                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)){
                        Text(text = stringResource(Res.string.setting_dark_auto), style = textButton(), modifier = Modifier.padding(start = 8.dp).weight(1f))

                        val textLanguage = if (autoPlay.value) stringResource(Res.string.setting_play_background_on)
                        else stringResource(Res.string.setting_play_background_off)
                        Text(text = textLanguage, style = textContentSecond(), modifier = Modifier.padding(start = 8.dp))
                        Switch(
                            modifier = Modifier.paddingStart16(),
                            checked = autoPlay.value,
                            onCheckedChange = { value ->
                                autoPlay.value = value
                            }
                        )
                    }
                }

                // Background
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
                    }) {
                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)){
                        Text(text = stringResource(Res.string.setting_play_background), style = textButton(), modifier = Modifier.padding(start = 8.dp).weight(1f))

                        val textLanguage = if (playOnBackground.value) stringResource(Res.string.setting_play_background_on)
                        else stringResource(Res.string.setting_play_background_off)
                        Text(text = textLanguage, style = textContentSecond(), modifier = Modifier.padding(start = 8.dp))
                        Switch(
                            modifier = Modifier.paddingStart16(),
                            checked = playOnBackground.value,
                            onCheckedChange = { value ->
                                playOnBackground.value = value
                            }
                        )
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