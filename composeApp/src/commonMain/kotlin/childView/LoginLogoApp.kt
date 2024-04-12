package childView

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_app
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import styles.paddingTop16StartEnd16

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginLogoApp() {
    Row(modifier = Modifier.fillMaxWidth().paddingTop16StartEnd16(), horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(Res.drawable.icon_app),
            contentDescription = "avatar",
            contentScale = ContentScale.FillWidth,            // crop the image if it's not a square
            modifier = Modifier
                .size(100.dp).aspectRatio(1f)
                .border(0.dp, Color.Transparent, CircleShape)   // add a border (optional)
        )
    }
}