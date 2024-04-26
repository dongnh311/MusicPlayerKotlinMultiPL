package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import base.BaseScreen
import com.seiko.imageloader.rememberImagePainter
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.topic_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.textTittleHome
import viewModel.TopicsViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class TopicsScreen: BaseScreen<TopicsViewModel>() {
    override var viewModel: TopicsViewModel = TopicsViewModel()

    // Screen detail
    val topicDetailScreen by lazy { TopicDetailScreen() }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
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
                    Text(text = stringResource(Res.string.topic_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                // Add item
                if (viewModel.listTopic.isEmpty()) {
                    item{
                        Text(text = stringResource(Res.string.home_ranking_empty), modifier = Modifier.fillMaxSize()
                            .paddingTop16(), textAlign = TextAlign.Center)
                    }
                } else {
                    items(viewModel.listTopic.size) { index ->
                        Card(modifier = Modifier
                            .paddingTop16StartEnd16().clickable(enabled = true) {
                                topicDetailScreen.topicModel = viewModel.listTopic[index]
                                navigator.push(topicDetailScreen)
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
                                Box(modifier = Modifier.fillParentMaxWidth()) {
                                    val itemTopic = viewModel.listTopic[index]
                                    val painter = if (itemTopic.urlImage.isNotEmpty()) {
                                        rememberImagePainter(itemTopic.urlImage)
                                    } else painterResource(Res.drawable.avatar_default)
                                    Image(
                                        painter = painter,
                                        contentDescription = "avatar",
                                        contentScale = ContentScale.FillWidth,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .fillMaxWidth().height(154.dp).aspectRatio(1f)
                                            .clip(RectangleShape)                       // clip to the circle shape
                                            .border(0.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                    )
                                }
                            })
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listTopic.isEmpty()) {
            viewModel.loadListTopics()
        }
    }

    override fun onDisposedScreen() {
    }
}