package styles

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun Modifier.paddingTop() = this.padding(top = 8.dp)

@Composable
fun iconSize24dp() = Modifier.size(24.dp)

@Composable
fun iconSize20dp() = Modifier.size(20.dp)