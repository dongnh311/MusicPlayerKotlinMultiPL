package view

import androidx.compose.runtime.Composable
import base.BaseScreen
import viewModel.SearchMusicViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SearchMusicScreen : BaseScreen<SearchMusicViewModel>() {

    override var viewModel: SearchMusicViewModel = SearchMusicViewModel()

    @Composable
    override fun makeContentForView() {
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}