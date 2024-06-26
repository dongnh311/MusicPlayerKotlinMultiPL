package view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import base.BaseScreen
import childView.EmptyDataView
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.OnMusicPlayCallBack
import model.MusicModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_add
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_active
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_unactive
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_next
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_pause
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_play
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_previous
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_app
import musicplayerkotlinmultipl.composeapp.generated.resources.music_detail_lyrics
import musicplayerkotlinmultipl.composeapp.generated.resources.music_detail_same_singer
import musicplayerkotlinmultipl.composeapp.generated.resources.music_detail_title
import musicplayerkotlinmultipl.composeapp.generated.resources.music_detail_topic
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import singleton.MusicPlayerSingleton.musicPlayerManager
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorDisable
import styles.colorPrimaryApp
import styles.colorPrimaryBackground
import styles.paddingTop16StartEnd16
import styles.paddingTop8
import styles.paddingTop8StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textNameMusicWhite
import styles.textSingerWhite
import styles.textTittleContent
import styles.textTittleHome
import utils.dialogs.DialogAddToPlaylist
import utils.exts.makeDurationToViewString
import utils.exts.toStringRemoveNull
import viewModel.MusicDetailViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class MusicDetailScreen: BaseScreen<MusicDetailViewModel>() {
    override var viewModel: MusicDetailViewModel = MusicDetailViewModel()

    // Save current object
    var musicModel = mutableStateOf(MusicModel())

    // To open dialog add
    private val isOpenDialogAddToPlaylist = mutableStateOf(false)

    private val progressPlayed = mutableStateOf(0.00f)
    private val statePlayMusic = mutableStateOf(false)

    private val isHavePreview = mutableStateOf(false)
    private val isHaveNext = mutableStateOf(false)

    private val durationOfMusic = mutableStateOf(0L)
    private val durationPlayed = mutableStateOf(0L)

    @OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
    @Composable
    override fun makeContentForView() {
        val topicDetailScreen by lazy { TopicDetailScreen() }

        val isPlay = remember { statePlayMusic }
        val progress = remember {  progressPlayed }

        val title = musicModel.value.name.ifEmpty { stringResource(Res.string.music_detail_title) }

        durationOfMusic.value = musicModel.value.duration.toLong() * 1000

        // find item player
        musicPlayerManager.currentItemPlayer()?.let {music ->
            if (music.id == musicModel.value.id) {
                statePlayMusic.value = true
                initHandelMusicCallBack()
            }
        }

        Scaffold(modifier = Modifier.background(Color.Red).fillMaxSize(), backgroundColor = colorPrimaryBackground,
            topBar = {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(Res.drawable.btn_back),
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Unspecified,
                        contentDescription = "Back",
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = title, style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top) {

                    // Main
                     item {
                         Box(modifier = Modifier.fillParentMaxWidth().height(200.dp)) {
                             val brush = Brush.horizontalGradient(listOf(Color.Red, colorPrimaryApp))
                             Canvas(
                                 modifier = Modifier.fillParentMaxWidth().height(200.dp),
                                 onDraw = {
                                     drawRoundRect(brush)
                                 }
                             )

                             Column(modifier = Modifier.padding(all = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                                 // Image and name
                                 Row(modifier = Modifier.fillMaxWidth(),
                                     verticalAlignment = Alignment.CenterVertically) {
                                     val painter = if (musicModel.value.imageUrl.isNotEmpty()) rememberImagePainter(musicModel.value.imageUrl) else painterResource(Res.drawable.icon_app)
                                     Image(
                                         painter = painter,
                                         contentDescription = "image",
                                         modifier = Modifier.size(120.dp),
                                         contentScale = ContentScale.FillWidth,
                                     )

                                     Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
                                         Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                                             // Favourite
                                             var colorTint = Color.Unspecified
                                             val iconFav = if (musicModel.value.isFavourite.value.isNotEmpty()) {
                                                 colorTint = Color.Red
                                                 painterResource(Res.drawable.btn_fav_active)
                                             } else painterResource(Res.drawable.btn_fav_unactive)
                                             IconButton(
                                                 onClick = {
                                                     updateFavorite()
                                                 },
                                                 modifier = Modifier.size(45.dp),
                                                 content = {
                                                     // Specify the icon using the icon parameter
                                                     androidx.compose.material.Icon(
                                                         painter = iconFav,
                                                         contentDescription = null,
                                                         modifier = Modifier.size(35.dp),
                                                         tint = colorTint,
                                                     )
                                                 }
                                             )

                                             // Add play list
                                             IconButton(
                                                 onClick = {
                                                    isOpenDialogAddToPlaylist.value = true
                                                 },
                                                 modifier = Modifier.size(45.dp).padding(start = 16.dp),
                                                 content = {
                                                     // Specify the icon using the icon parameter
                                                     androidx.compose.material.Icon(
                                                         painter = painterResource(Res.drawable.btn_add),
                                                         contentDescription = null,
                                                         modifier = Modifier.size(35.dp),
                                                         tint = Color.Unspecified,
                                                     )
                                                 }
                                             )
                                         }
                                         Text(text = musicModel.value.name, style = textNameMusicWhite())
                                         Text(text = musicModel.value.singerModel?.name.toStringRemoveNull(), style = textSingerWhite(), modifier = Modifier.padding(top = 8.dp))
                                     }
                                 }

                                 LinearProgressIndicator(
                                     progress = { progress.value },
                                     modifier = Modifier.height(12.dp).fillMaxWidth().clip(RoundedCornerShape(0.dp)).paddingTop8(),
                                     color = Color.White,
                                     trackColor = Color.Black,
                                     strokeCap = StrokeCap.Butt
                                 )

                                 // Button play
                                 Row(modifier = Modifier.fillMaxWidth().paddingTop8(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                     val colorDisablePrevious = if (isHavePreview.value) Color.Unspecified else colorDisable
                                     // Previous
                                    IconButton(
                                        onClick = {

                                        },
                                        enabled = isHavePreview.value,
                                        modifier = Modifier.size(45.dp),
                                        content = {
                                            // Specify the icon using the icon parameter
                                            androidx.compose.material.Icon(
                                                painter = painterResource(Res.drawable.btn_previous),
                                                contentDescription = null,
                                                modifier = Modifier.size(35.dp),
                                                tint = colorDisablePrevious,
                                            )
                                        }
                                    )

                                     if (isPlay.value) {
                                         // Pause
                                         IconButton(
                                             onClick = {
                                                 musicPlayerManager.pause()
                                                 isPlay.value = false
                                             },
                                             modifier = Modifier.size(45.dp),
                                             content = {
                                                 // Specify the icon using the icon parameter
                                                 androidx.compose.material.Icon(
                                                     painter = painterResource(Res.drawable.btn_pause),
                                                     contentDescription = null,
                                                     modifier = Modifier.size(35.dp),
                                                     tint = Color.Unspecified,
                                                 )
                                             }
                                         )
                                     } else {
                                         // Play
                                         IconButton(
                                             onClick = {
                                                 startPlayMusic()
                                                 musicPlayerManager.initPlayer()
                                                 musicPlayerManager.addOnlyOneMusicToPlay(musicModel.value)
                                                 if (durationPlayed.value != 0L) musicPlayerManager.play(durationPlayed.value)
                                                 else musicPlayerManager.play(null)

                                                 isPlay.value = true
                                             },
                                             modifier = Modifier.size(45.dp),
                                             content = {
                                                 // Specify the icon using the icon parameter
                                                 androidx.compose.material.Icon(
                                                     painter = painterResource(Res.drawable.btn_play),
                                                     contentDescription = null,
                                                     modifier = Modifier.size(35.dp),
                                                     tint = Color.Unspecified,
                                                 )
                                             }
                                         )
                                     }

                                     // Next
                                     val colorDisableNext = if (isHaveNext.value) Color.Unspecified else colorDisable
                                     IconButton(
                                         onClick = {

                                         },
                                         modifier = Modifier.size(45.dp),
                                         content = {
                                             // Specify the icon using the icon parameter
                                             androidx.compose.material.Icon(
                                                 painter = painterResource(Res.drawable.btn_next),
                                                 contentDescription = null,
                                                 modifier = Modifier.size(35.dp),
                                                 tint = colorDisableNext,
                                             )
                                         }
                                     )

                                     //Space
                                     Spacer(modifier = Modifier.weight(1f))
                                     Row {
                                         Text(text = makeDurationToViewString(durationPlayed.value), style = textSingerWhite())
                                         Text(text = "/", style = textSingerWhite())
                                         Text(text = makeDurationToViewString(durationOfMusic.value), style = textSingerWhite())
                                     }
                                 }
                             }
                         }
                     }

                    // Title Lyric
                    item {
                        Text(text = stringResource(Res.string.music_detail_lyrics), style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop16StartEnd16())
                    }

                    // Lyric
                    item {
                        Text(text = musicModel.value.lyrics, style = textContentPrimary(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
                    }

                    // Same singer title
                    item {
                        Text(text = stringResource(Res.string.music_detail_same_singer), style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop16StartEnd16())
                    }

                    // Show music
                    if (viewModel.listMusicsSameSinger.isEmpty()) {
                        item {
                            EmptyDataView()
                        }
                    } else {
                        items(viewModel.listMusicsSameSinger.size) { index ->
                            Card(modifier = Modifier
                                .fillMaxWidth().paddingTop16StartEnd16().clickable(enabled = true) {
                                    this@MusicDetailScreen.musicModel.value = viewModel.listMusicsSameSinger[index]
                                    onStartedScreen()
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
                                        val itemMusic = viewModel.listMusicsSameSinger[index]
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
                                })
                        }
                    }

                    // Topic
                    if (viewModel.listTopic.isNotEmpty()) {
                        // Title Topic
                        item {
                            Text(text =stringResource(Res.string.music_detail_topic), style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
                        }

                        item {
                            FlowRow(modifier = Modifier,
                                horizontalArrangement = Arrangement.Start, verticalArrangement = Arrangement.SpaceAround,
                                maxItemsInEachRow = viewModel.listTopic.size
                            ) {
                                viewModel.listTopic.forEach { item ->
                                    Card(modifier = Modifier
                                        .paddingTop16StartEnd16().clickable(enabled = true) {
                                            topicDetailScreen.topicModel = item
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
                                            // Add another single item
                                            Column(modifier = Modifier.paddingTop8StartEnd16()) {
                                                Text(text = "#" + item.name, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                                            }
                                        })
                                }
                            }
                        }
                    }

                    // Last item
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }

        // Open dialog add
        if (isOpenDialogAddToPlaylist.value) {
            DialogAddToPlaylist(
                isOpenDialogAddToPlaylist,
                playlists = viewModel.listPlaylist,
                callbackCreate = {
                },
                callBackAdd = {
                    it.forEach {playlist ->
                        viewModel.addMusicToPlaylist(playlist.id, musicModel.value.id)
                    }
                }
            )
        }
    }

    override fun onStartedScreen() {
        if (viewModel.saveMusic.id != musicModel.value.id) {
            viewModel.loadInformationMore(musicModel.value)
        }
    }

    override fun onDisposedScreen() {
    }

    /**
     * Make music play
     */
    private fun startPlayMusic() {
        viewModel.writePlayMusicToHistory()
        initHandelMusicCallBack()
    }

    /**
     * Handle callback if need
     */
    private fun initHandelMusicCallBack() {
        musicPlayerManager.onMusicPlayCallBack = object : OnMusicPlayCallBack {
            override fun onPlayingItem(
                durationPlaying: Long,
                totalDuration: Long,
                musicId: String
            ) {
                if (musicModel.value.id == musicId) {
                    val durationPercent =  (durationPlaying.toFloat()/totalDuration.toFloat())
                    Logger.e("Duration played: $durationPlaying/$totalDuration, percent: $durationPercent")
                    statePlayMusic.value = true
                    progressPlayed.value = durationPercent
                    if (durationOfMusic.value == 0L) { durationOfMusic.value = totalDuration }
                    durationPlayed.value = durationPlaying
                }
            }

            override fun onPlayDone(musicId: String) {
                if (musicId == musicModel.value.id) {
                    statePlayMusic.value = false
                    progressPlayed.value = 0.00f
                    durationPlayed.value = 0L
                }
            }

            override fun onMoveNextItem(musicModel: MusicModel) {
            }

            override fun onMovePrevious(musicModel: MusicModel) {
            }
        }
    }

    /**
     * Update fav
     */
    private fun updateFavorite() {
        viewModel.updateOrDeleteFavouriteItem(musicModel.value.isFavourite.value.isEmpty())
    }
}