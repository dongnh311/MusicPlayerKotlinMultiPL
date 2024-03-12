package viewModel

import base.BaseViewModel
import network.services.EventServices
import org.koin.core.component.get
import org.koin.core.component.inject

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class HomeViewModel: BaseViewModel() {
    private val eventServices: EventServices = get()

    fun loadEvents() {
        workingWithApiHaveDialog(
            service = {
                eventServices.loadListNotify("https://raw.githubusercontent.com/dongnh311/mockup-api/main/notify.json")
            },
            progressInBackground = {},
            progressInLayout = {},
            onErrorThrowable ={}
        )
    }
}