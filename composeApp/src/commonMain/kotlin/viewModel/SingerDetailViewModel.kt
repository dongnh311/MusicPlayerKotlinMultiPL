package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import kotlinx.coroutines.flow.first
import model.MusicModel
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SingerDetailViewModel: BaseViewModel() {

    // List music of singer
    val listMusicOfSinger = mutableStateListOf<MusicModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save last id singer search
    var singerIdLast = ""

    /**
     * Find all music of singer
     *
     * @param singerId
     */
    fun findListMusicOfSinger(singerId: String) {
        singerIdLast = singerId
        workingWithApiHaveDialog(
            service = {
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                // Find music of sing
                val listMusic = mutableListOf<MusicModel>()
                musics.forEach { item ->
                    // Map singer
                    item.singerModel = singers.find { singer -> singer.id == item.singerId }
                    if (item.singerId == singerId) {
                        listMusic.add(item)
                    }
                }
                listMusic
            },
            doOnBeforeService = {},
            doOnAfterService = {
                listMusicOfSinger.clear()
                listMusicOfSinger.addAll(it)
            },
            onErrorThrowable = {}
        )
    }
}