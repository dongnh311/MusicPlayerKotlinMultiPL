package styles

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        textAlign = TextAlign.Start,
        color = colorPrimaryText
    )
}

@Composable
fun textTittleContent() : TextStyle {
    return TextStyle(
        fontSize = 22.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorPrimaryText,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textNameMusicWhite() : TextStyle {
    return TextStyle(
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorWhite,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textSingerWhite() : TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorDisable,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textButton() : TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorPrimaryText,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textContentPrimary() : TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorPrimaryText,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textRankingPrimary() : TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorPrimaryText,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textContentSecond() : TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorSecondText,
        textAlign = TextAlign.Start
    )
}

@Composable
fun textContentThree() : TextStyle {
    return TextStyle(
        fontSize = 12.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Normal,
        color = colorThreeText,
        textAlign = TextAlign.Start
    )
}