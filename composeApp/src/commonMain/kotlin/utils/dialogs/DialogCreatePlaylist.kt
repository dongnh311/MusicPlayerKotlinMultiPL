package utils.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import childView.InputTextField
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_playlist_btn_create
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_playlist_name
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_playlist_name_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_playlist_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import singleton.MusicPlayerSingleton.loadMaxWidthDialog
import singleton.MusicPlayerSingleton.screenSize
import styles.buttonDialog
import styles.textTittleContent
import utils.exts.dpToPx
import utils.exts.pxToDp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DialogCreatePlaylist(isShowDialog: MutableState<Boolean>,
                         playlistName : MutableState<String>,
                         callback: (String) -> Unit) {
    val isEnableButtonSave = mutableStateOf(false)

    val keyboardController = LocalSoftwareKeyboardController.current // Keyboard control

    if (isShowDialog.value) {

        BasicAlertDialog(onDismissRequest = {
            isShowDialog.value = false
        }, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = false)) {
            Surface(
                modifier = Modifier
                    .width(loadMaxWidthDialog())
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                content = {
                    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                        // Title
                        Text(text = stringResource(Res.string.dialog_playlist_title), style = textTittleContent(), modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                        // Name
                        InputTextField(
                            value = playlistName.value,
                            onChange = {newName ->
                                playlistName.value = newName
                                isEnableButtonSave.value = playlistName.value.isNotEmpty()
                            },
                            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
                            label = stringResource(Res.string.dialog_playlist_name),
                            placeholder = stringResource(Res.string.dialog_playlist_name_pl),
                            leadingIcon = {
                                Icon(
                                    painterResource(Res.drawable.btn_playlist),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(30.dp),
                                )
                            }, keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            )
                        )

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
                                    callback.invoke(playlistName.value)
                                },
                                enabled = isEnableButtonSave.value
                            ) {
                                Text(text = stringResource(Res.string.dialog_playlist_btn_create))
                            }
                        }
                    }
                }
            )
        }
    }
}


