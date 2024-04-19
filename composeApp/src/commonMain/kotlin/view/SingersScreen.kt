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
import const.GENDER_MALE
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender_female
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_gender_male
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_title
import musicplayerkotlinmultipl.composeapp.generated.resources.singers_year_of_birth
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16
import styles.paddingTop16StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleHome
import viewModel.SingersViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SingersScreen : BaseScreen<SingersViewModel> () {
    override var viewModel: SingersViewModel = SingersViewModel()

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val singerDetailScreen by lazy { SingerDetailScreen() }
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
                    Text(text = stringResource(Res.string.singers_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                // Add item
                if (viewModel.listSingers.isEmpty()) {
                    item{
                        Text(text = stringResource(Res.string.home_ranking_empty), modifier = Modifier.fillMaxSize()
                            .paddingTop16(), textAlign = TextAlign.Center)
                    }
                } else {
                    items(viewModel.listSingers.size) { index ->
                        Card(modifier = Modifier
                            .paddingTop16StartEnd16().clickable(enabled = true) {
                                singerDetailScreen.singerModel = viewModel.listSingers[index]
                                navigator.push(singerDetailScreen)
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
                                val itemSinger = viewModel.listSingers[index]
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    val painter = if (itemSinger.avatar.isNotEmpty()) {
                                        rememberImagePainter(itemSinger.avatar)
                                    } else painterResource(Res.drawable.avatar_default)
                                    Image(
                                        painter = painter,
                                        contentDescription = "avatar",
                                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .fillMaxWidth().aspectRatio(1f)
                                            .clip(RectangleShape)                       // clip to the circle shape
                                            .border(0.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                    )

                                    // Name
                                    Text(text = itemSinger.name, modifier = Modifier.fillParentMaxWidth().padding(top = 16.dp),
                                        textAlign = TextAlign.Center, style = textContentPrimary())

                                    // Gender
                                    Text(text = stringResource(Res.string.singers_gender) + " " + findGenderForSinger(itemSinger.gender)
                                        , modifier = Modifier.fillParentMaxWidth(), textAlign = TextAlign.Center,
                                        style = textContentSecond()
                                    )

                                    // Year
                                    Text(text = stringResource(Res.string.singers_year_of_birth) + " " + itemSinger.yearOfBirth
                                        , modifier = Modifier.fillParentMaxWidth().padding(bottom = 16.dp), textAlign = TextAlign.Center,
                                        style = textContentSecond()
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
        if (viewModel.listSingers.isEmpty()) {
            viewModel.loadSingers()
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