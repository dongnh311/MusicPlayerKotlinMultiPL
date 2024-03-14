package view

import Greeting
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.BaseScreen
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import childView.PagerIndicator
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import model.EventModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import singleton.ViewManager
import styles.paddingStartEnd
import styles.paddingTop
import styles.paddingTopStartEnd
import styles.textTittleContent
import viewModel.EventDetailViewModel
import viewModel.HomeViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

class HomeScreen: BaseScreen<HomeViewModel>() {

    private var listEvents =  mutableListOf<EventModel>()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun makeContentForView() {
        viewModel = HomeViewModel()

        val detailScreen = EventDetailScreen()

//        if (ViewManager.parentNavigation != null) {
//            ViewManager.parentNavigation?.push(detailScreen)
//        } else {
//            navigator.push(detailScreen)
//        }

        val pagerState = rememberPagerState(initialPage = 0, pageCount = {return@rememberPagerState listEvents.size})
        val coroutineScope = rememberCoroutineScope()
        var pageSize by remember { mutableStateOf(IntSize.Zero) }
        val lastIndex by remember(pagerState.currentPage) {
            derivedStateOf { pagerState.currentPage == listEvents.size - 1 }
        }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Events", style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTopStartEnd())

            Box(Modifier.fillMaxWidth(), ) {
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
                                .shadow(1.dp, RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
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

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                // Add a single item
                item {
                    Text(text = "First item", modifier = Modifier.paddingTopStartEnd())
                }

                // Add 5 items
                items(100) { index ->
                    Text(text = "Item: $index")
                }

                // Add another single item
                item {
                    Text(text = "Last item")
                }
            }
        }
    }

    override fun onStartedScreen() {
        viewModel.screenModelScope.launch {
            viewModel.loadEvents().collect {
                if (it != null) listEvents = it
            }
        }
    }

    override fun onDisposedScreen() {
    }


}