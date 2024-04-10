package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.model.screenModelScope
import childView.PagerIndicator
import com.seiko.imageloader.rememberImagePainter
import commonShare.DecimalFormat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.EventModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import singleton.ViewManager
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop
import styles.paddingTop16StartEnd16
import styles.paddingTop8StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent
import viewModel.HomeViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class HomeScreen: BaseScreen<HomeViewModel>() {

    override var viewModel: HomeViewModel = HomeViewModel()

    private var listEvents =  mutableListOf<EventModel>()

    @OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val detailScreen = EventDetailScreen()

        val pagerState = rememberPagerState(initialPage = 0, pageCount = {return@rememberPagerState listEvents.size})
        val coroutineScope = rememberCoroutineScope()

        val listMusicNew = remember { viewModel.listNewMusics }

        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Text("Events", style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
            }
            item {
                Box(Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth().paddingTop(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,)
                    {
                        HorizontalPager(
                            state = pagerState,
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .shadow(10.dp, RoundedCornerShape(8.dp))
                                    .background(Color.White)
                                    .fillMaxWidth()
                                    .height(200.dp).clickable {
                                                if (ViewManager.parentNavigation != null) {
                                                    ViewManager.parentNavigation?.push(detailScreen)
                                                } else {
                                                    navigator.push(detailScreen)
                                                }
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                val painter = if (listEvents[it].image != null) rememberImagePainter(listEvents[it].image!!)  else rememberImagePainter("https://..")
                                Image(
                                    painter = painter,
                                    contentDescription = "image",
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.FillWidth,
                                )
                            }
                        }
                    }

                    PagerIndicator(pagerState = pagerState, modifier = Modifier
                        .align(Alignment.BottomCenter).padding(bottom = 8.dp)) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(it)
                        }
                    }
                }
            }

            // Title news
            item {
                Text(text = "News", style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
            }

            // Add 5 items
            items(listMusicNew.size) { index ->
                Card(modifier = Modifier
                    .fillMaxWidth().paddingTop16StartEnd16().clickable(enabled = true) {

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
                            val itemMusic = listMusicNew[index]
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
                                Text(text = "Singers : " + itemMusic.singerModel?.name, style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                })
            }

            // Add another single item
            item {
                Text(text = "Last item")
            }
        }
    }

    override fun onStartedScreen() {
        // Load list event
        if (listEvents.isEmpty()) {
            viewModel.screenModelScope.launch {
                viewModel.loadEvents().collect {
                    if (it != null) listEvents = it
                }
            }
        }

        // Load data music
        if (viewModel.listNewMusics.isEmpty()) {
            viewModel.loadListMusicAndSinger()
        }
    }

    override fun onDisposedScreen() {
    }


}