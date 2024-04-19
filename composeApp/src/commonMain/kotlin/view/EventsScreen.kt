package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.model.screenModelScope
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.events_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16StartEnd16
import styles.primaryDark
import styles.textTittleContent
import styles.textTittleHome
import viewModel.EventsViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class EventsScreen : BaseScreen<EventsViewModel>() {
    override var viewModel: EventsViewModel = EventsViewModel()

    // Show detail
    private val eventDetailScreen = EventDetailScreen()

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
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.events_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    items(viewModel.listEvent.size) { index ->
                        Card(modifier = Modifier
                            .fillMaxWidth().paddingTop16StartEnd16().clickable(enabled = true) {
                                eventDetailScreen.eventModel = viewModel.listEvent[index]
                                navigator.push(eventDetailScreen)
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
                                Column (
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    val itemEvent = viewModel.listEvent[index]
                                    val painter = if (itemEvent.image?.isNotEmpty() == true) {
                                        rememberImagePainter(itemEvent.image!!)
                                    } else painterResource(Res.drawable.avatar_default)
                                    Image(
                                        painter = painter,
                                        contentDescription = "avatar",
                                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .fillMaxWidth().heightIn(max = 200.dp)
                                            .aspectRatio(1f)
                                            .clip(RectangleShape)                       // clip to the circle shape
                                            .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                    )

                                    // Add another single item
                                    Column(modifier = Modifier.fillMaxWidth().background(primaryDark).padding(all = 8.dp)) {
                                        Text(text = itemEvent.title, style = textTittleContent())
                                    }
                                }
                            })
                    }

                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
        }
    }

    override fun onStartedScreen() {
        // Load list event
        if (viewModel.listEvent.isEmpty()) {
            viewModel.screenModelScope.launch {
                viewModel.loadEvents().collect {
                    if (it != null) viewModel.listEvent.addAll(it)
                }
            }
        }
    }

    override fun onDisposedScreen() {

    }
}