package styles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 14/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */


@Composable
fun Modifier.paddingStartEnd() = this.padding(start = 16.dp, end = 16.dp)

@Composable
fun Modifier.paddingTopStartEnd() = this.padding(start = 16.dp, end = 16.dp, top = 8.dp)

@Composable
fun Modifier.paddingTopBottom() = this.padding(top = 8.dp, bottom = 8.dp)

@Composable
fun Modifier.paddingTop() = this.padding(top = 8.dp)

@Composable
fun iconSize24dp() = Modifier.size(24.dp)

@Composable
fun iconSize20dp() = Modifier.size(20.dp)

@Composable
fun iconSize28dp() = Modifier.size(28.dp)

@Composable
fun iconSize30dp() = Modifier.size(30.dp)

@Composable
fun iconSize32dp() = Modifier.size(32.dp)

@Composable
fun Modifier.buttonSize32dp() = Modifier.size(32.dp)

@Composable
fun buttonDialog() = Modifier.clip(RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp))

@Composable
fun Modifier.backgroundGradient() = Modifier
    .background(
        brush = Brush.verticalGradient(
            startY = 0f,
            endY = 1000f,
            colors = listOf(
                primaryLight,
                colorAccountLight
            )
        )
    )

@Composable
fun Modifier.buttonCommonModifier() = Modifier.width(200.dp).height(45.dp)
