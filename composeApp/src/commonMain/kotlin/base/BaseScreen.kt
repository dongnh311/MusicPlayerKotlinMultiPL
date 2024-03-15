package base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import utils.dialogs.ShowDialogLoading
import utils.interfaces.OnAPIErrorEvent
import utils.interfaces.OnAPIRequestEvent
import kotlin.reflect.typeOf


/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
abstract class BaseScreen<V: BaseViewModel> : Screen, OnAPIRequestEvent, OnAPIErrorEvent {

    // Navigation
   lateinit var navigator: Navigator

   // viewModel
   abstract var viewModel: V

   // Save value
   lateinit var isShowLoading: MutableState<Boolean>

    @Composable
    final override fun Content() {
        navigator = LocalNavigator.currentOrThrow
        isShowLoading = remember { mutableStateOf(false) }

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

        if (isShowLoading.value) {
            ShowDialogLoading {
                Logger.e("Dismiss dialog loading")
            }
        }

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
        isShowLoading.value = true
    }

    override fun onStopCallApi() {
        isShowLoading.value = false
    }

    override fun onUpdate(status: Int) {
    }
}