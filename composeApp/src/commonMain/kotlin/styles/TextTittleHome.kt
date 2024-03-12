package styles

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

/**
 * Text style for title of screen
 *
 * @return TextStyle
 */
@Composable
fun textTittleHome() : TextStyle {
    return TextStyle(
        fontSize = 26.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        color = colorPrimaryText
    )
}