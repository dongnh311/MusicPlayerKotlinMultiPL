package utils.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_cancel
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_ok
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import singleton.MusicPlayerSingleton.loadMaxWidthDialog
import singleton.MusicPlayerSingleton.screenSize
import styles.buttonColorsCancel
import styles.buttonDialog
import styles.textContentPrimary
import styles.textTittleContent
import utils.exts.dpToPx
import utils.exts.pxToDp

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun ShowDialogConfirm(
    isShowDialog: MutableState<Boolean>,
    title: String = "",
    content: String,
    textButtonLeft: String = stringResource(Res.string.btn_cancel),
    textButtonRight: String = stringResource(Res.string.btn_ok),
    callBackLeft: ()-> Unit,
    callBackRight: ()-> Unit
) {
    if (isShowDialog.value) {

        BasicAlertDialog(onDismissRequest = {
            isShowDialog.value = false
            callBackLeft.invoke()
        }, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false, usePlatformDefaultWidth = false)) {
            Surface(
                modifier = Modifier
                    .width(loadMaxWidthDialog())
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Title
                    if (title.isNotEmpty()) {
                        Text(text = title, style = textTittleContent(), modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                    }

                    // Content
                    Text(text = content, style = textContentPrimary(), modifier = Modifier.fillMaxWidth().padding(all = 16.dp))

                    // Button
                    Row(
                        modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = buttonDialog().weight(1f).padding(end = 4.dp),
                            shape = RoundedCornerShape(3.dp),
                            border = BorderStroke(0.dp, Color.Transparent),
                            onClick = {
                                isShowDialog.value = false
                                callBackLeft.invoke()
                            },
                            colors = buttonColorsCancel()
                        ) {
                            Text(textButtonLeft)
                        }
                        Button(
                            modifier = buttonDialog().weight(1f).padding(start = 4.dp),
                            shape = RoundedCornerShape(3.dp),
                            border = BorderStroke(0.dp, Color.Transparent),
                            onClick = {
                                isShowDialog.value = false
                                callBackRight.invoke()
                            }
                        ) {
                            Text(textButtonRight)
                        }
                    }
                }
            }
        }

    }
}