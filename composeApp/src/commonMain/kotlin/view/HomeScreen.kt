package view

import Greeting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class HomeScreen: Screen {
    @Composable
    @Preview
    override fun Content() {
        LifecycleEffect(
            onStarted = { Logger.e {"onStarted HomeScreen"} },
            onDisposed = { Logger.e("onDisposed HomeScreen") }
        )

        val greetingList = remember { Greeting().greetList() }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { Logger.e("Click on button") }) {
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
        }
    }
}