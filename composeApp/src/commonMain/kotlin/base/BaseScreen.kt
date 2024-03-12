package base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import org.koin.core.component.KoinComponent
import utils.interfaces.OnAPIErrorEvent
import utils.interfaces.OnAPIRequestEvent
import kotlin.reflect.typeOf


/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
abstract class BaseScreen<V: BaseViewModel> : Screen, KoinComponent, OnAPIRequestEvent, OnAPIErrorEvent {

    // Navigation
   lateinit var navigator: Navigator

   // viewModel
   lateinit var viewModel: V

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

        viewModel.onApiRequestEvent = this@BaseScreen
        viewModel.onAPIErrorEvent = this@BaseScreen
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

    override fun onErrorThrow(error: Throwable) {
    }

    override fun onErrorString(error: String) {
    }

    override fun onErrorInt(error: Int) {
    }

    override fun onStartCallApi() {
    }

    override fun onStopCallApi() {
    }

    override fun onUpdate(status: Int) {
    }
}