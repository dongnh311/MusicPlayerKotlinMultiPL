package utils.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 14/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogLoading(isShowDialog: MutableState<Boolean>, onDismissRequest: ()->Unit) {
    if (isShowDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                onDismissRequest.invoke()
                isShowDialog.value = false
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp).fillMaxWidth(2f),
                    elevation = 8.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.padding(top = 48.dp))
                        Text("Loading", modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
                    }
                }
            }
        }
    }

}

