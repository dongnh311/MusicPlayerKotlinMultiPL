package styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

// Background
val colorPrimaryBackground: Color @Composable get() = if (isSystemInDarkTheme()) colorBlack else colorWhite

val backgroundColor = Color(0xFFFFFFFF)
val onBackground = Color(0xFF19191C)
val primaryLight = Color(0xFF9400C8)
val primaryLightVariant = Color(0xFFf2ffff)
val lightSecondary = Color(0xFFefd8bf)
val lightSecondaryVariant = Color(0xFFefd8bf)

val colorBlack = Color(0xFF000000)
val colorWhite = Color(0xFFFFFFFF)
val redErrorDark = Color(0xFFB00020)
val redErrorLight = Color(0xFFEF5350)

val primaryDark = Color(0xFF9400C8)
val primaryDarkVariant = Color(0xFF00001a)
val darkSecondary = Color(0xFF402810)
val darkSecondaryVariant = Color(0xFF200000)

val colorDisable = Color(0xFFCCCCCC)
val colorFacebook = Color(0xFF4267B2)
val colorMainApp = Color(0xFF7209B7)

val colorAccountLight = Color(0xFFBF45EA)
val colorAccountDark = Color(0xFFBF45EA)

val colorPrimaryApp = Color(0xFF9400C8)

val colorThreeText = Color(0xFFA6ACAF)

val colorGreen = Color(0x7061FF7E)

// Text
val colorPrimaryText: Color @Composable get() = if (!isSystemInDarkTheme()) colorBlack else colorWhite
val colorSecondText: Color @Composable get() = if (!isSystemInDarkTheme()) darkSecondary else lightSecondary

val colorAccountCard: Color @Composable get() = if (!isSystemInDarkTheme()) colorWhite else colorAccountDark

val colorButtonCancel: Color @Composable get() = if (isSystemInDarkTheme()) colorAccountDark else colorWhite

private val lightThemeColors = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLightVariant,
    onPrimary = colorBlack,
    secondary = lightSecondary,
    secondaryVariant = lightSecondaryVariant,
    onSecondary = colorBlack,
    error = redErrorDark,
    onError = redErrorLight,
    background = backgroundColor,
    onBackground = onBackground,
    )

private val darkThemeColors = darkColors(
    primary = primaryDark,
    primaryVariant = primaryDarkVariant,
    onPrimary = colorWhite,
    secondary = darkSecondary,
    secondaryVariant = darkSecondaryVariant,
    onSecondary = colorWhite,
    error = redErrorLight,
    onError = redErrorLight,
    background = backgroundColor,
    onBackground = onBackground
)

/**
 * Load theme color
 *
 * @param isDarkMode
 * @return
 */
fun loadThemeConfig(isDarkMode: Boolean) : Colors {
    return if (isDarkMode) darkThemeColors else lightThemeColors
}
