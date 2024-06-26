package base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utils.interfaces.OnAPIErrorEvent
import utils.interfaces.OnAPIRequestEvent

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
abstract class BaseViewModel: ScreenModel {
    // Call back on call api
    var onApiRequestEvent : OnAPIRequestEvent? = null

    // Call back on fail
    var onAPIErrorEvent: OnAPIErrorEvent? = null

    // Save IO coroutine context
    val dispatchersIO = Dispatchers.IO

    // Save main coroutine context
    val dispatchersMain = Dispatchers.Main

    // Handle error of coroutine
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        run {
            Logger.e("coroutineExceptionHandler : ", t)
            this@BaseViewModel.screenModelScope.launch {
                    onApiRequestEvent?.onStopCallApi()
            }
        }
    }

    // CoroutineScope on background
    val coroutineScope = CoroutineScope(dispatchersIO + coroutineExceptionHandler)

    final override fun onDispose() {
        super.onDispose()
        Logger.e("onDispose ScreenModel Name : ${this@BaseViewModel}")
        onApiRequestEvent?.onStopCallApi()
    }

    /**
     * Show dialog when load data from server
     */
    fun startWorking() {
        onApiRequestEvent?.onStartCallApi()
    }

    /**
     * Hide dialog when dialog is showing
     */

    fun stopWorking() {
        onApiRequestEvent?.onStopCallApi()
    }

    /**
     * Update status when download or upload file
     */
    fun onUpdateStatus(status: Int) {
        onApiRequestEvent?.onUpdate(status)
    }

    /**
     * Make function call with Coroutine not return value
     *
     * @param service : Block code
     */
    fun workingTaskWithIO(
        service: () -> Unit
    ) {
        this@BaseViewModel.coroutineScope.launch(dispatchersIO + coroutineExceptionHandler) {
            try {
                service.invoke()
            } catch (e: Exception) {
                Logger.e("workingTaskWithIO ",e)
            }
        }
    }

    /**
     * Make function call start with Coroutine
     *
     * @param service : Block code
     * @param nextProgressStep : Block code callback
     */
    inline fun <reified T> workingTaskIOReturn(
        crossinline service: () -> T,
        crossinline nextProgressStep: (T) -> Unit
    ) {
        this@BaseViewModel.coroutineScope.launch(dispatchersIO + coroutineExceptionHandler) {
            try {
                val result = service.invoke()
                if (result != null) {
                    screenModelScope.launch {
                        nextProgressStep.invoke(result)
                    }
                } else {
                    screenModelScope.launch {
                        Throwable("Wrong type or null is return.")
                    }
                }
            } catch (e: Exception) {
                Logger.e("workingTaskIOReturn ", e)
            }
        }
    }

    /**
     * Make function call start with Coroutine
     *
     * @param service : Block code
     * @param nextProgressStep : Block code callback
     */
    inline fun <reified T> workingWithApiNonDialog(
        crossinline service: suspend () -> T,
        crossinline nextProgressStep: (T) -> Unit
    ) {
        this@BaseViewModel.coroutineScope.launch {
            try {
                val result = service.invoke()
                if (result != null) {
                    screenModelScope.launch {
                        nextProgressStep.invoke(result)
                    }
                } else {
                    screenModelScope.launch {
                        Throwable("Wrong type or null is return.")
                    }
                }
            } catch (e: Exception) {
                Logger.e("workingTaskIOReturn ", e)
            }
        }
    }

    /**
     * Base method call Api with dialog
     *
     * @param service : API service call
     * @param doOnBeforeService : Task doing on background
     * @param doOnAfterService : task doing on UI thread
     * @param onErrorThrowable : send back error
     */
    inline fun <reified T> workingWithApiHaveDialog(
        noinline service: suspend () -> T,
        crossinline doOnBeforeService: () -> Unit,
        crossinline doBeforeCloseDialog: (T) -> Unit = {},
        crossinline doOnAfterService: (T) -> Unit,
        crossinline onErrorThrowable: (Throwable) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                // Open dialog
                this@BaseViewModel.screenModelScope.launch {
                    startWorking()
                }

                // Do some thing on background
                doOnBeforeService.invoke()

                // Wait response of all task
                val responses = withContext(dispatchersIO) {
                    return@withContext service.invoke()
                }

                if (responses != null) {
                    // Do any thing before close dialog
                    coroutineScope.launch {
                        doBeforeCloseDialog.invoke(responses)
                    }
                    // Disable dialog and call update on UI
                    this@BaseViewModel.screenModelScope.launch {
                        stopWorking()
                        doOnAfterService.invoke(responses)
                    }
                } else {
                    // Disable dialog and call update on UI
                    this@BaseViewModel.screenModelScope.launch {
                        stopWorking()
                        onErrorThrowable.invoke(Throwable("Wrong type return"))
                    }
                }
            } catch (e: Exception) {
                Logger.e("workingWithApiHaveDialog", e)
                this@BaseViewModel.screenModelScope.launch {
                    stopWorking()
                    onErrorThrowable.invoke(e)
                }
            }
        }
    }
}