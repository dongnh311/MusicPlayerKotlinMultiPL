package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import kotlinx.coroutines.flow.first
import model.SingerModel
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SingersViewModel: BaseViewModel() {

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save list singers
    val listSingers = mutableStateListOf<SingerModel>()

    /**
     * Load all singers
     */
    fun loadSingers() {
        workingWithApiHaveDialog(
            service = {
                firebaseMusicsHelper.loadListSingerOnFB().first()
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listSingers.addAll(it)
            },
            onErrorThrowable = {}
        )
    }
}