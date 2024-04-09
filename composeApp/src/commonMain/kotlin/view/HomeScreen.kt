package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import base.BaseScreen
import cafe.adriel.voyager.core.model.screenModelScope
import childView.PagerIndicator
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import model.EventModel
import singleton.ViewManager
import styles.colorPrimaryBackground
import styles.paddingTop
import styles.paddingTop8StartEnd16
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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun makeContentForView() {
        val detailScreen = EventDetailScreen()

        val pagerState = rememberPagerState(initialPage = 0, pageCount = {return@rememberPagerState listEvents.size})
        val coroutineScope = rememberCoroutineScope()

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

            // Add a single item
            item {
                Text(text = "News", style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
            }

            // Add 5 items
            items(101) { index ->
                Text(text = "Item: $index", modifier = Modifier.fillParentMaxWidth())
            }

            // Add another single item
            item {
                Text(text = "Last item")
            }

        }
    }

    override fun onStartedScreen() {
        if (listEvents.isEmpty()) {
            viewModel.screenModelScope.launch {
                viewModel.loadEvents().collect {
                    if (it != null) listEvents = it
                }
            }
        }
    }

    override fun onDisposedScreen() {
    }


}