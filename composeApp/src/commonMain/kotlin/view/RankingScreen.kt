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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import base.BaseScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import childView.EmptyDataView
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import model.MusicModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_billboard_global
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_cn
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_kr
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_rap
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_trending
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_us
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_vn
import musicplayerkotlinmultipl.composeapp.generated.resources.home_title
import musicplayerkotlinmultipl.composeapp.generated.resources.ranking_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textRankingPrimary
import styles.textTittleContent
import styles.textTittleHome
import viewModel.HomeViewModel
import viewModel.RankingViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class RankingScreen: BaseScreen<RankingViewModel>() {

    override var viewModel: RankingViewModel  = RankingViewModel()

    @OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
    @Composable
    override fun makeContentForView() {
        val pagerState = rememberPagerState(0, pageCount = { 7 })

        val listTitleTab = mutableListOf(
            stringResource(Res.string.home_ranking_trending),
            stringResource(Res.string.home_ranking_vn),
            stringResource(Res.string.home_ranking_us),
            stringResource(Res.string.home_ranking_billboard_global),
            stringResource(Res.string.home_ranking_kr),
            stringResource(Res.string.home_ranking_cn),
            stringResource(Res.string.home_ranking_rap))

        Column (modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxWidth().height(45.dp), contentAlignment = Alignment.CenterStart) {
                Text(text = stringResource(Res.string.ranking_title), style = textTittleHome(), modifier = Modifier.fillMaxWidth().padding(start = 16.dp))
            }

            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.White,
                edgePadding = 0.dp,
                modifier = Modifier.fillMaxWidth(),
                divider = {},
                tabs = {
                    for (index in 0 until 7) {
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { viewModel.screenModelScope.launch { pagerState.scrollToPage(index) }},
                            text = {
                                Text(
                                    text = listTitleTab[index],
                                    color = if (pagerState.currentPage == index) Color.Blue else Color.Gray,
                                    style = textContentPrimary(),
                                )
                            },
                            modifier = Modifier
                                .zIndex(1f)
                                .padding(end = 8.dp)
                                .height(height = 36.dp)
                        )
                    }
                }
            )
            Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth()) // add divider here
            HorizontalPager(
                key = {
                    it
                },
                modifier = Modifier.fillMaxWidth(),
                pageSize =  PageSize.Fill,
                state = pagerState
            ) {indexPage ->
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center) {
                    when (indexPage) {
                        0 -> {
                            showTrendingTab()
                        }
                        1 -> {
                            showVpopTab()
                        }
                        2 -> {
                            showUSUKTab()
                        }
                        3 -> {
                            EmptyDataView()
                        }
                        4 -> {
                            showKpopTab()
                        }
                        5 -> {
                            showCpopTab()
                        }
                        6 -> {
                            EmptyDataView()
                        }
                        else -> {
                            EmptyDataView()
                        }
                    }

                }
            }
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listTrending.isEmpty()) {
            viewModel.loadListMusic()
        }
    }

    override fun onDisposedScreen() {
    }

    @Composable
    private fun showTrendingTab() {
        val listTrending = remember { viewModel.listTrending }
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            items(listTrending.size) { index ->
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
                        ItemViewRanking((index + 1).toString(),  listTrending[index])
                    })
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun showVpopTab() {
        val listVpop = remember { viewModel.listVpop }
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            items(listVpop.size) { index ->
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
                        ItemViewRanking((index + 1).toString(),  listVpop[index])
                    })
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun showUSUKTab() {
        val listUs = remember { viewModel.listUspop }
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            items(listUs.size) { index ->
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
                        ItemViewRanking((index + 1).toString(),  listUs[index])
                    })
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun showKpopTab() {
        val listKpop = remember { viewModel.listKpop }
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            items(listKpop.size) { index ->
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
                        ItemViewRanking((index + 1).toString(),  listKpop[index])
                    })
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    private fun showCpopTab() {
        val listCpop = remember { viewModel.listCpop }
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
            colorPrimaryBackground
        ), horizontalAlignment = Alignment.CenterHorizontally) {
            items(listCpop.size) { index ->
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
                        ItemViewRanking((index + 1).toString(),  listCpop[index])
                    })
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun ItemViewRanking(index: String, musicModel: MusicModel) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = index, style = textRankingPrimary(), modifier = Modifier.padding(end = 16.dp))

            val painter = if (musicModel.imageUrl.isNotEmpty()) {
                rememberImagePainter(musicModel.imageUrl)
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
                Text(text = musicModel.name, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                Text(text = musicModel.singerModel?.name.toString(), style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}