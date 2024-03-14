package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import base.BaseScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import viewModel.AccountViewModel
import viewModel.MainViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AccountScreen : BaseScreen<AccountViewModel>(){

    @Composable
    override fun makeContentForView() {
        viewModel = AccountViewModel()

        Scaffold(modifier = Modifier.background(Color.Red).fillMaxSize()) {
            Text("AccountScreen")
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}