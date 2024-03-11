package view

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class AccountScreen : Screen{

    @Composable
    override fun Content() {
        LifecycleEffect(
            onStarted = { Logger.e {"onStarted HomeScreen"} },
            onDisposed = { Logger.e("onDisposed HomeScreen") }
        )
    }
}