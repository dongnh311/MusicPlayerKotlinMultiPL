package childView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_empty_data
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.colorPrimaryText
import styles.paddingTop16
import styles.paddingTop8

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyDataView() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        val painter = painterResource(Res.drawable.icon_empty_data)
        Image(
            painter = painter,
            contentDescription = "image",
            modifier = Modifier.size(50.dp).paddingTop16().aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Text(text = stringResource(Res.string.home_ranking_empty), modifier = Modifier.fillMaxWidth()
            .paddingTop8(), textAlign = TextAlign.Center, color = colorPrimaryText
        )
    }
}