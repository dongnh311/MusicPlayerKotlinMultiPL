package styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
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
val primaryLight = Color(0xFFbfd5ef)
val primaryLightVariant = Color(0xFFf2ffff)
val lightSecondary = Color(0xFFefd8bf)
val lightSecondaryVariant = Color(0xFFefd8bf)

val colorBlack = Color(0xFF000000)
val colorWhite = Color(0xFFFFFFFF)
val RedErrorDark = Color(0xFFB00020)
val RedErrorLight = Color(0xFFEF5350)

val primaryDark = Color(0xFF102840)
val primaryDarkVariant = Color(0xFF00001a)
val darkSecondary = Color(0xFF402810)
val darkSecondaryVariant = Color(0xFF200000)

val colorDisable = Color(0xFFCCCCCC)
val colorFacebook = Color(0xFF4267B2)
val colorMainApp = Color(0xFF7209B7)

// Text
val colorPrimaryText: Color @Composable get() = if (!isSystemInDarkTheme()) colorBlack else colorWhite

private val lightThemeColors = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLightVariant,
    onPrimary = colorBlack,
    secondary = lightSecondary,
    secondaryVariant = lightSecondaryVariant,
    onSecondary = colorBlack,
    error = RedErrorDark,
    onError = RedErrorLight,
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
    error = RedErrorLight,
    onError = RedErrorLight,
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
