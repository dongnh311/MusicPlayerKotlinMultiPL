package styles

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@Composable
fun buttonColorsGoogle() = ButtonDefaults.buttonColors(
    backgroundColor = colorWhite,
    disabledBackgroundColor = colorDisable,
)

@Composable
fun buttonColorsFacebook() = ButtonDefaults.buttonColors(
    backgroundColor = colorFacebook,
    disabledBackgroundColor = colorDisable,
)

@Composable
fun buttonColorsEmail() = ButtonDefaults.buttonColors(
    backgroundColor = colorMainApp,
    disabledBackgroundColor = colorDisable,
)