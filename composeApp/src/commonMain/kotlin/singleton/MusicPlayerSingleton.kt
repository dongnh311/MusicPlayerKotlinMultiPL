package singleton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import utils.exts.dpToPx
import utils.exts.pxToDp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
object MusicPlayerSingleton {
    // Check user login
    val isLogin = MutableStateFlow(false)

    // Save screen size
    val screenSize = mutableStateOf(Pair(-1, -1))

    /**
     * Load max width for dialog
     *
     * @return Dp
     */
    @Composable
    fun loadMaxWidthDialog(): Dp {
        var newWidth = screenSize.value.first * 0.9F
        val maxWidth = 370.dp.dpToPx()
        println("ScreenWidth: ${screenSize.value.first}, 0.9% ScreenWith: $newWidth ,limit width: $maxWidth")

        if (newWidth > maxWidth || newWidth < 0) {
            newWidth = maxWidth
        }

        return newWidth.pxToDp()
    }

    /**
     * Load max height to dialog
     *
     * @return
     */
    @Composable
    fun loadMaxHeightDialog() : Dp {
        var newHeight = screenSize.value.second * 0.5F
        val maxHeight = 570.dp.dpToPx()
        println("ScreenHeight: ${screenSize.value.second}, 0.5% ScreenHeight: $newHeight ,limit width: $maxHeight")

        if (newHeight > maxHeight || newHeight < 0) {
            newHeight = maxHeight
        }

        return newHeight.pxToDp()
    }

    /**
     * Load max height to bottom sheet
     *
     * @return
     */
    @Composable
    fun loadHeightBottomSheet() : Dp {
        var newHeight = screenSize.value.second * 0.8F
        val maxHeight = 650.dp.dpToPx()
        println("ScreenHeight: ${screenSize.value.second}, 0.8% ScreenHeight: $newHeight ,limit width: $maxHeight")

        if (newHeight > maxHeight || newHeight < 0) {
            newHeight = maxHeight
        }

        return newHeight.pxToDp()
    }
}