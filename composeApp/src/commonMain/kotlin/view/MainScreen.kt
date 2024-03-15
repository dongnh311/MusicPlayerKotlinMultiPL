package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import base.BaseScreen
import base.BaseViewModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import childView.TabAccountScreen
import childView.TabHomeScreen
import childView.TabRankingScreen
import singleton.ViewManager
import styles.colorPrimaryBackground
import styles.textTittleHome
import viewModel.MainViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class MainScreen : BaseScreen<MainViewModel>() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun makeContentForView() {
        viewModel = MainViewModel()

        val tabHomeScreen = remember { TabHomeScreen }
        val tabRankingScreen = remember { TabRankingScreen }
        val tabAccountScreen = remember { TabAccountScreen }

        ViewManager.parentNavigation = navigator
        TabNavigator(
            tabHomeScreen,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(tabHomeScreen, tabRankingScreen, tabAccountScreen)
                )
            },
        ) { tabNavigator ->
            Scaffold(
                modifier = Modifier.fillMaxSize().background(colorPrimaryBackground),
                topBar = {
                    TopAppBar(
                        title = { Text(text = tabNavigator.current.options.title, style = textTittleHome()) }
                    )
                },
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = it.calculateBottomPadding()
                            )
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(tabHomeScreen)
                        TabNavigationItem(tabRankingScreen)
                        TabNavigationItem(tabAccountScreen)
                    }
                }
            )
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}