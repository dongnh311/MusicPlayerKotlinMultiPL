package viewModel

import androidx.compose.runtime.mutableStateListOf
import base.BaseViewModel
import const.FAVOURITE_MUSIC
import kotlinx.coroutines.flow.first
import model.FavouriteModel
import model.MusicModel
import utils.helper.FirebaseFavouriteHelper
import utils.helper.FirebaseMusicsHelper
import utils.helper.FirebaseUserHelper

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class FavouriteViewModel: BaseViewModel() {
    // Fav
    private val firebaseFavouriteHelper = FirebaseFavouriteHelper()

    // User helper
    private val firebaseUserHelper = FirebaseUserHelper()

    // Handle music
    private val firebaseMusicsHelper = FirebaseMusicsHelper()

    // List Music Fav
    val listMusicFavourite = mutableStateListOf<FavouriteModel>()

    /**
     * Load list fav
     *
     */
    fun loadAllFavourite() {
        workingWithApiHaveDialog(
            service = {
                val listFav = firebaseFavouriteHelper.loadListFavouriteFromFB(firebaseUserHelper.loadUserId()).first()
                val musics = firebaseMusicsHelper.loadListMusicsOnFB().first()
                val singers = firebaseMusicsHelper.loadListSingerOnFB().first()
                musics.forEach { music ->
                    music.singerModel = singers.find { singer -> singer.id == music.singerId }
                }

                // Return list

                listFav.forEach { fav ->
                    if (fav.favType == FAVOURITE_MUSIC) {
                        val musicByFav = musics.find { music -> fav.favouriteItemId == music.id }
                        fav.musicItem = musicByFav
                    }
                }
                listFav

            },
            doOnBeforeService = {},
            doOnAfterService = {
                listMusicFavourite.clear()
                listMusicFavourite.addAll(it)
            }
        )
    }

    /**
     * Delete fav
     *
     * @param favouriteModel
     */
    fun deleteFav(favouriteModel: FavouriteModel) {
        workingWithApiHaveDialog(
            service = {
                firebaseFavouriteHelper.deleteFavourite(favouriteModel.id)
            },
            doOnBeforeService = {},
            doOnAfterService = {
                loadAllFavourite()
            }
        )
    }
}