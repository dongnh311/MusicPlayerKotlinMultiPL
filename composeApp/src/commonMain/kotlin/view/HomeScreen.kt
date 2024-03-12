package view

import Greeting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import co.touchlab.kermit.Logger
import org.jetbrains.compose.ui.tooling.preview.Preview
import singleton.ViewManager
import viewModel.EventDetailViewModel
import viewModel.HomeViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class HomeScreen: BaseScreen<HomeViewModel>() {

    @Composable
    override fun makeContentForView() {
        val greetingList = remember { Greeting().greetList() }
        val detailScreen = EventDetailScreen()
        viewModel = getScreenModel<HomeViewModel>()

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                if (ViewManager.parentNavigation != null) {
                    ViewManager.parentNavigation?.push(detailScreen)
                } else {
                    navigator.push(detailScreen)
                }
                Logger.e("Click on button add screen")
                navigator.push(detailScreen)
            }) {
                Text("Click me!")
            }
            Column(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                greetingList.forEach { greeting ->
                    Text(greeting)
                    Divider()
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                // Add a single item
                item {
                    Text(text = "First item")
                }

                // Add 5 items
                items(100) { index ->
                    Text(text = "Item: $index")
                }

                // Add another single item
                item {
                    Text(text = "Last item")
                }
            }
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }


}