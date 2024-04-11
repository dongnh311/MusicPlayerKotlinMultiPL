package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import model.EventModel
import network.response.NotifyResponse
import network.services.EventServices
import singleton.NetworkManager

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class EventsViewModel: BaseViewModel() {
    // Api
    private val eventServices: EventServices = NetworkManager.apiNetRest.ktorfit.create()

    // Save list event
    val listEvent = mutableStateListOf<EventModel>()

    /**
     * Load list event
     */
    fun loadEvents() = callbackFlow {
        workingWithApiHaveDialog(
            service = {
                eventServices.loadListNotify("https://raw.githubusercontent.com/dongnh311/mockup-api/main/notify.json")
            },
            progressInBackground = {},
            progressInLayout = {
                Logger.e("Data : ${it.body()}")
                if (it.isSuccessful) {
                    try {
                        val responseBody = it.body()
                        var listDataReturn: MutableList<EventModel>? = mutableListOf()
                        responseBody?.let { data ->
                            listDataReturn = NetworkManager.jsonManager.decodeFromString<NotifyResponse>(
                                data
                            ).data
                        }
                        trySend(listDataReturn)
                    } catch (e: Exception) {
                        Logger.e("loadListNotify", e)
                    }
                }
            },
            onErrorThrowable ={}
        )
        awaitClose {
            close()
        }
    }

}