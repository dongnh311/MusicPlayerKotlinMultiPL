package utils.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import styles.textContentPrimary
import styles.textTittleContent

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 01/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogConfirm(
    isShowDialog: MutableState<Boolean>,
    title: String = "",
    content: String,
    textButtonLeft: String = "Cancel",
    textButtonRight: String = "OK",
    callBackLeft: ()-> Unit,
    callBackRight: ()-> Unit
) {
    if (isShowDialog.value) {
        BasicAlertDialog(onDismissRequest = {
            isShowDialog.value = false
            callBackLeft.invoke()
        }, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Title
                    if (title.isNotEmpty()) {
                        Text(text = title, style = textTittleContent())
                    }

                    // Content
                    Text(text = content, style = textContentPrimary(), modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp))

                    // Button
                    Row(
                        modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            onClick = {
                                isShowDialog.value = false
                                callBackLeft.invoke()
                            }
                        ) {
                            Text(textButtonLeft)
                        }
                        Button(
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
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