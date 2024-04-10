package viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import base.BaseViewModel
import const.COUNTRY_CN
import const.COUNTRY_KR
import const.COUNTRY_US
import const.COUNTRY_VN
import const.FB_DATABASE_MUSICS
import const.FB_DATABASE_SINGER
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.MusicModel
import model.SingerModel
import utils.helper.FirebaseMusicsHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class RankingViewModel: BaseViewModel() {

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // Save list singers
    val listSingers = mutableStateListOf<SingerModel>()

    // Save list singers
    private val listMusics = mutableStateListOf<MusicModel>()

    // Save list trending
    val listTrending = mutableStateListOf<MusicModel>()

    // Save list vn
    val listVpop = mutableStateListOf<MusicModel>()

    // Save list US-UK
    val listUspop = mutableStateListOf<MusicModel>()

    // Save list Billboard Global
    val listBl = mutableStateListOf<MusicModel>()

    // Save list K-pop
    val listKpop = mutableStateListOf<MusicModel>()

    // Save list c-Pop
    val listCpop = mutableStateListOf<MusicModel>()

    /**
     * Load music
     */
    fun loadListMusic() {
        workingWithApiHaveDialog(
            service = {
                // Load singers
                var singers = firebaseMusicsHelper.loadListSingerOnFB().first()
                if (singers.isEmpty()) {
                    singers = firebaseMusicsHelper.writeDataSingerToFB()
                }

                // Convert image url
                loadImageSingerFirebase(singers).first()
                listSingers.addAll(singers.toMutableStateList())

                // Load musics
                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()
                // Load new image path
                val listMusicUpdate = loadImageMusicFirebase(musics).first()

                listMusics.clear()
                listMusics.addAll(listMusicUpdate)
            },
            progressInBackground = {},
            progressInLayout = {
                listTrending.clear()
                listTrending.addAll(listMusics)

                listVpop.addAll(listMusics.filter { item -> item.country == COUNTRY_VN })
                listUspop.addAll(listMusics.filter { item -> item.country == COUNTRY_US })
                listKpop.addAll(listMusics.filter { item -> item.country == COUNTRY_KR })
                listCpop.addAll(listMusics.filter { item -> item.country == COUNTRY_CN })
            },
            onErrorThrowable = {}
        )
    }

    /**
     * Find new url image
     *
     * @param musics
     */
    private fun loadImageMusicFirebase(musics : MutableList<MusicModel>) = callbackFlow {
        coroutineScope.launch {
            for (item in musics) {
                if (item.imageUrl.startsWith("gs://")) {
                    // Thumb
                    val newUrlImage =  firebaseMusicsHelper.findUrlLoadImage(item.imageUrl)
                    item.imageUrl = newUrlImage
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_MUSICS, item.id, "imageUrl", newUrlImage)
                }

                if (item.url.startsWith("gs://")) {
                    // File to play
                    val newUrlFile = firebaseMusicsHelper.findUrlLoadImage(item.url)
                    item.url = newUrlFile
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_MUSICS, item.id, "url", newUrlFile)
                }

                // Find singer
                item.singerModel = listSingers.find { singer -> singer.id == item.singerId }
            }
            trySend(musics)
        }

        awaitClose {
            close()
        }
    }

    /**
     * Load avatar for singer
     *
     * @param singers
     */
    private fun loadImageSingerFirebase(singers: MutableList<SingerModel>) = callbackFlow {
        coroutineScope.launch {
            for (item in singers) {
                if (item.avatar.startsWith("gs://")) {
                    // Thumb
                    val newAvatar =  firebaseMusicsHelper.findUrlLoadImage(item.avatar)
                    item.avatar = newAvatar
                    firebaseMusicsHelper.updateNewValueForMusic(FB_DATABASE_SINGER, item.id, "avatar", newAvatar)
                }
            }
            trySend(singers)
        }

        awaitClose {
            close()
        }
    }
}