package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import base.BaseScreen
import childView.EmptyDataView
import childView.LastItemForListView
import com.seiko.imageloader.rememberImagePainter
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.play_history_confirm_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.play_history_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleHome
import utils.dialogs.ShowDialogConfirm
import viewModel.PlayHistoryViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PlayHistoryScreen: BaseScreen<PlayHistoryViewModel>() {

    override var viewModel: PlayHistoryViewModel = PlayHistoryViewModel()

    // Open dialog confirm
    private val isOpenDialogCheckDeleteAll = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val musicDetailScreen by lazy { MusicDetailScreen() }
        Scaffold(modifier = Modifier.background(Color.Red).fillMaxSize(), backgroundColor = colorPrimaryBackground,
            topBar = {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(Res.drawable.btn_back),
                        contentDescription = "Back",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Unspecified,
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.play_history_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.fillMaxWidth().weight(1f).height(45.dp))

                    androidx.compose.material3.IconButton(onClick = {
                        if (viewModel.listPlayHistory.isNotEmpty()) {
                            isOpenDialogCheckDeleteAll.value = true
                        }
                    }, enabled = viewModel.listPlayHistory.isNotEmpty()) {
                        Icon(
                            painterResource(Res.drawable.btn_delete),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = buttonSize32dp()
                                .clickable(viewModel.listPlayHistory.isNotEmpty()) {
                                    isOpenDialogCheckDeleteAll.value = true
                                }
                        )
                    }
                }
            }) {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()), contentAlignment = Alignment.Center) {
                if (viewModel.listPlayHistory.isEmpty()) {
                    EmptyDataView()
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                        items(viewModel.listPlayHistory.size) {index ->
                            Card(modifier = Modifier
                                .fillMaxWidth().paddingTop16StartEnd16().clickable(enabled = true) {
                                    viewModel.listPlayHistory[index].musicModel?.let { music ->
                                        musicDetailScreen.musicModel.value = music
                                        navigator.push(musicDetailScreen)
                                    }
                                },
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
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        val itemMusic = viewModel.listPlayHistory[index].musicModel
                                        itemMusic?.let {
                                            val painter = if (itemMusic.imageUrl.isNotEmpty()) {
                                                rememberImagePainter(itemMusic.imageUrl)
                                            } else painterResource(Res.drawable.avatar_default)
                                            Image(
                                                painter = painter,
                                                contentDescription = "avatar",
                                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .aspectRatio(1f)
                                                    .clip(RectangleShape)                       // clip to the circle shape
                                                    .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                            )

                                            // Add another single item
                                            Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
                                                Text(text = itemMusic.name, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                                                Text(text = itemMusic.singerModel?.name.toString(), style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                            }
                                        }
                                    }
                                })
                        }
                        item {
                            LastItemForListView()
                        }
                    }
                }
            }
        }

        // Confirm delete
        if (isOpenDialogCheckDeleteAll.value) {
            ShowDialogConfirm(
                isOpenDialogCheckDeleteAll,
                title = "",
                content = stringResource(Res.string.play_history_confirm_delete),
                textButtonRight = stringResource(Res.string.btn_delete),
                callBackLeft = {
                    isOpenDialogCheckDeleteAll.value = false
                },
                callBackRight = {
                    isOpenDialogCheckDeleteAll.value = false
                    viewModel.deleteAllHistory()
                }
            )
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listPlayHistory.isEmpty()) {
            viewModel.loadListPlayed()
        }
    }

    override fun onDisposedScreen() {
    }
}