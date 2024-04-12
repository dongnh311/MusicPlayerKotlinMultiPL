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
}