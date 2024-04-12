package utils.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.seiko.imageloader.rememberImagePainter
import const.LIMIT_MUSIC_ON_PLAY_LIST
import model.PlayListModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_add
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_add_btn_add
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_add_playlist_title
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_count_musics
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_create_new_pl
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import singleton.MusicPlayerSingleton
import singleton.MusicPlayerSingleton.loadMaxHeightDialog
import styles.buttonDialog
import styles.colorAccountCard
import styles.paddingAll16
import styles.paddingAll8
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.paddingTop8
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun DialogAddToPlaylist(
    isShowDialog: MutableState<Boolean>,
    playlists: MutableList<PlayListModel>,
    callbackCreate: () -> Unit,
    callBackAdd: (MutableList<PlayListModel>) -> Unit
) {
    val countSelect = remember { mutableStateOf(0) }
    if (isShowDialog.value) {
        BasicAlertDialog(onDismissRequest = {
            isShowDialog.value = false
        }, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = false)) {
            Surface(
                modifier = Modifier
                    .width(MusicPlayerSingleton.loadMaxWidthDialog())
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    // Title
                    Text(text = stringResource(Res.string.dialog_add_playlist_title), style = textTittleContent(), modifier = Modifier.padding(start = 8.dp, end = 8.dp))

                    // Content
                    if (playlists.isEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            IconButton(
                                onClick = {
                                    // Create new play list
                                    callbackCreate.invoke()
                                    isShowDialog.value = false
                                },
                                modifier = Modifier.size(50.dp).paddingTop16().aspectRatio(1f),
                                content = @Composable {
                                    // Specify the icon using the icon parameter
                                    Icon(
                                        painter = painterResource(Res.drawable.btn_add),
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Unspecified,
                                    )
                                }
                            )
                            Text(text = stringResource(Res.string.playlist_create_new_pl), modifier = Modifier.fillMaxWidth()
                                .paddingTop8(), textAlign = TextAlign.Center)
                        }
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxWidth().height(loadMaxHeightDialog()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                             items(playlists.size) {index ->
                                 val itemPlaylist = playlists[index]
                                 Card(modifier = Modifier
                                     .fillMaxWidth().paddingTop16(),
                                     elevation = CardDefaults.cardElevation(
                                         defaultElevation =  10.dp,
                                     ),
                                     shape = RoundedCornerShape(
                                         topEnd = 5.dp,
                                         topStart = 5.dp,
                                         bottomEnd = 5.dp,
                                         bottomStart = 5.dp
                                     ),
                                     colors = CardDefaults.cardColors(
                                         containerColor = colorAccountCard
                                     ),
                                     content = {
                                         Row(
                                             horizontalArrangement = Arrangement.Start,
                                             verticalAlignment = Alignment.CenterVertically,
                                             modifier = Modifier
                                                 .fillMaxWidth().paddingAll8()
                                         ) {
                                             // Check
                                             val enableCheck = itemPlaylist.listMusicsId.size < LIMIT_MUSIC_ON_PLAY_LIST
                                             Checkbox (
                                                 checked = itemPlaylist.isChoose.value,
                                                 onCheckedChange = {value->
                                                     itemPlaylist.isChoose.value = value
                                                     if (value) {
                                                         countSelect.value++
                                                     } else {
                                                         countSelect.value--
                                                     }

                                                 },
                                                 enabled = enableCheck,
                                                 modifier = Modifier.size(50.dp)
                                             )

                                             // Image
                                             if (itemPlaylist.thumbnail.isNotEmpty()) {
                                                 Image(
                                                     painter = rememberImagePainter(itemPlaylist.thumbnail),
                                                     contentDescription = "avatar",
                                                     contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                                     modifier = Modifier
                                                         .size(50.dp)
                                                         .aspectRatio(1f)
                                                         .clip(RectangleShape)                       // clip to the circle shape
                                                         .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                                 )
                                             } else {
                                                 androidx.compose.material3.IconButton(
                                                     onClick = {},
                                                     modifier = Modifier.size(50.dp).clip(RectangleShape)   // clip to the circle shape
                                                         .border(2.dp, Color.Gray, RectangleShape),  // add a border (optional),
                                                     content = {
                                                         // Specify the icon using the icon parameter
                                                         androidx.compose.material.Icon(
                                                             painter = painterResource(Res.drawable.btn_playlist),
                                                             contentDescription = null,
                                                             modifier = Modifier.size(30.dp),
                                                             tint = Color.Unspecified,
                                                         )
                                                         Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                                     }
                                                 )
                                             }

                                             // Name and count
                                             Column(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 16.dp)) {
                                                 Text(text = itemPlaylist.name, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                                                 Text(text = stringResource(Res.string.playlist_count_musics) + "${itemPlaylist.listMusicsId.size}/$LIMIT_MUSIC_ON_PLAY_LIST", style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                             }
                                         }
                                     })
                             }
                        }
                    }

                    // Button
                    Row(
                        modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = buttonDialog().weight(1f).padding(start = 4.dp),
                            shape = RoundedCornerShape(3.dp),
                            border = BorderStroke(0.dp, Color.Transparent),
                            onClick = {
                                isShowDialog.value = false
                                val list = playlists.filter { item -> item.isChoose.value }
                                callBackAdd.invoke(list.toMutableList())

                                // Reset value
                                playlists.forEach {
                                        item -> item.isChoose.value = false
                                }
                            }, enabled = countSelect.value > 0
                        ) {
                            Text(text = stringResource(Res.string.dialog_add_btn_add), style = textContentPrimary(),
                                modifier = Modifier.fillMaxWidth().padding(all = 16.dp), textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}