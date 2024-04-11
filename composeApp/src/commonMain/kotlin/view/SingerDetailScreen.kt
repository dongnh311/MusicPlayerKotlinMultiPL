package view

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import childView.EmptyDataView
import com.seiko.imageloader.rememberImagePainter
import const.GENDER_MALE
import model.SingerModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_empty_data
import musicplayerkotlinmultipl.composeapp.generated.resources.singer_musics
import musicplayerkotlinmultipl.composeapp.generated.resources.singer_title
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender_female
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender_male
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_year_of_birth
import musicplayerkotlinmultipl.composeapp.generated.resources.topic_title
import musicplayerkotlinmultipl.composeapp.generated.resources.wall_paper
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.paddingTop8
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleHome
import viewModel.SingerDetailViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SingerDetailScreen: BaseScreen<SingerDetailViewModel>() {
    override var viewModel: SingerDetailViewModel = SingerDetailViewModel()

    // Save current singer
    var singerModel = SingerModel()

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
                        modifier = Modifier
                            .buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.singer_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {

            Column(modifier = Modifier.fillMaxWidth().padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                LazyColumn(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top) {

                    item {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            // Wallpaper
                            Image(
                                painter = painterResource(Res.drawable.wall_paper),
                                contentDescription = "Wallpaper",
                                modifier = Modifier.fillMaxWidth().height(220.dp),
                                contentScale = ContentScale.FillWidth,
                            )

                            Column(modifier = Modifier.align(Alignment.BottomCenter).padding(top = 155.dp),
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                                val painter = if (singerModel.avatar.isNotEmpty()) rememberImagePainter(singerModel.avatar) else painterResource(Res.drawable.avatar_default)
                                // Avatar
                                Image(
                                    painter = painter,
                                    contentDescription = "Wallpaper",
                                    modifier = Modifier.size(110.dp).clip(CircleShape)   // clip to the circle shape
                                        .border(2.dp, Color.Gray, CircleShape),
                                    contentScale = ContentScale.FillWidth,
                                )

                                // Name
                                Text(text = singerModel.name, style = textContentPrimary(), modifier = Modifier.fillMaxWidth().paddingTop8(), textAlign = TextAlign.Center)

                                // Gender
                                Text(text = stringResource(Res.string.singers_gender) + " " + findGenderForSinger(singerModel.gender)
                                    , modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                                    style = textContentSecond()
                                )

                                // Year
                                Text(text = stringResource(Res.string.singers_year_of_birth) + " " + singerModel.yearOfBirth
                                    , modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), textAlign = TextAlign.Center,
                                    style = textContentSecond()
                                )
                            }
                        }
                    }

                    // Music
                    item {
                        Text(text = stringResource(Res.string.singer_musics), style = textContentPrimary(), modifier = Modifier.fillMaxWidth().paddingTop16StartEnd16(), textAlign = TextAlign.Start)
                    }

                    // Add item
                    if (viewModel.listMusicOfSinger.isEmpty()) {
                        item {
                            EmptyDataView()
                        }
                    } else {
                        items(viewModel.listMusicOfSinger.size) { index ->
                            Card(modifier = Modifier
                                .paddingTop16StartEnd16().clickable(enabled = true) {

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
                                        val itemMusic = viewModel.listMusicOfSinger[index]
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

                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }

    override fun onStartedScreen() {
        if (viewModel.singerIdLast != singerModel.id) {
            viewModel.findListMusicOfSinger(singerModel.id)
        }
    }

    override fun onDisposedScreen() {
    }

    /**
     * Find gender for singer
     *
     * @param genderType
     * @return
     */
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun findGenderForSinger(genderType: Int) : String {
        return if (genderType == GENDER_MALE) {
            stringResource(Res.string.singers_gender_male)
        } else {
            stringResource(Res.string.singers_gender_female)
        }
    }
}