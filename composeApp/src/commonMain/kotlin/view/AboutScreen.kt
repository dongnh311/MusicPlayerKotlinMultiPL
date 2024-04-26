package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import base.BaseScreen
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.about_title
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorPrimaryBackground
import styles.textButton
import styles.textTittleHome
import viewModel.AboutViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AboutScreen: BaseScreen<AboutViewModel>() {

    override var viewModel: AboutViewModel = AboutViewModel()

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
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Unspecified,
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.about_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()), contentAlignment = Alignment.Center) {
                Text(text = stringResource(Res.string.about_title), style = textButton(), modifier = Modifier.padding(start = 8.dp))
            }
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}