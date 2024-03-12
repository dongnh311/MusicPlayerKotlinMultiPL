package base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
abstract class BaseScreen : Screen {

   lateinit var navigator: Navigator

    @Composable
    final override fun Content() {
        navigator = LocalNavigator.currentOrThrow
        LifecycleEffect(
            onStarted = {
                Logger.e {"onStarted ${this.key}"}
                onStartedScreen()
        },
            onDisposed = {
                Logger.e("onDisposed ${this.key}")
                onDisposedScreen()
            }

        )
        makeContentForView()
    }

    /**
     * Add view from here
     */
    @Composable
    abstract fun makeContentForView()

    /**
     * Event when screen start
     */
    abstract fun onStartedScreen()

    /**
     * Event when screen disposed
     */
    abstract fun onDisposedScreen()
}