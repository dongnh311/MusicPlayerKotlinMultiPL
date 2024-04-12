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
class NewMusicViewModel: BaseViewModel() {
    val listNewMusic = mutableStateListOf<MusicModel>()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    /**
     * Load list new music
     */
    fun loadNewMusics() {
        workingWithApiHaveDialog(
            service = {
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()

                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()

                musics.forEach {item ->
                    // Find singer
                    item.singerModel = singers.find { singer -> singer.id == item.singerId }
                }

                val musicsNew = firebaseMusicsHelper.loadListNewMusicsOnFB().first()

                val listMusicNewLocal = mutableListOf<MusicModel>()
                for (musicId in musicsNew) {
                    val find = musics.find { item -> musicId.id == item.id }
                    find?.let {
                        listMusicNewLocal.add(find)
                    }
                }

                listMusicNewLocal
            },
            doOnAfterService = {
                listNewMusic.clear()
                listNewMusic.addAll(it)
            },
            doOnBeforeService = {},
            onErrorThrowable = {}
        )
    }
}