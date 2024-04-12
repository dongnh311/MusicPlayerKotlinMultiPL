package styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.sp
import singleton.MusicPlayerSingleton.screenSize

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@Composable
fun MusicPlayerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = loadThemeConfig(isSystemInDarkTheme())
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
            Layout(
                content = {
                    content()
                },
                measurePolicy = { measurable, constraints ->
                    // Use the max width and height from the constraints
                    val width = constraints.maxWidth
                    val height = constraints.maxHeight

                    screenSize.value = Pair(width, height)
                    println("Width: $width, height: $height")

                    // Measure and place children composable
                    val placeable = measurable.map { main ->
                        main.measure(constraints)
                    }

                    layout(width, height) {
                        var yPosition = 0
                        placeable.forEach { placeable ->
                            placeable.placeRelative(x = 0, y = yPosition)
                            yPosition += placeable.height
                        }
                    }
                }
            )

        }
    }
}

