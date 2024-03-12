package view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import base.BaseScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import co.touchlab.kermit.Logger
import viewModel.HomeViewModel
import viewModel.RankingViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class RankingScreen: BaseScreen<RankingViewModel>() {

    @Composable
    override fun makeContentForView() {
        viewModel = getScreenModel<RankingViewModel>()
        Text("Ranking Screen!")
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}