package view

import androidx.compose.runtime.Composable
import base.BaseScreen
import viewModel.PickImageViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 02/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PickImageScreen : BaseScreen<PickImageViewModel>() {
    override var viewModel: PickImageViewModel = PickImageViewModel()

    @Composable
    override fun makeContentForView() {
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}