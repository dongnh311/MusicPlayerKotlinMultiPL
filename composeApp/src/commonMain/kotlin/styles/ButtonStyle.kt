package styles

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

@Composable
fun buttonColorsCancel() = ButtonDefaults.buttonColors(
    backgroundColor = colorButtonCancel,
    disabledBackgroundColor = colorDisable,
)

@Composable
fun buttonColorAccount() = androidx.compose.material3.ButtonDefaults.buttonColors(
    containerColor = colorPrimaryBackground,
    contentColor = colorPrimaryText,
    disabledContainerColor = colorDisable,
    disabledContentColor = colorDisable
)

