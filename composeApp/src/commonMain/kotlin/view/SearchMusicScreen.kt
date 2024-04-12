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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import base.BaseScreen
import childView.EmptyDataView
import childView.InputTextField
import com.seiko.imageloader.rememberImagePainter
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_add
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_unactive
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_search
import musicplayerkotlinmultipl.composeapp.generated.resources.new_music_title
import musicplayerkotlinmultipl.composeapp.generated.resources.search_musics_hint
import musicplayerkotlinmultipl.composeapp.generated.resources.search_musics_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16StartEnd16
import styles.paddingTop8StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textContentThree
import styles.textTittleHome
import viewModel.SearchMusicViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class SearchMusicScreen : BaseScreen<SearchMusicViewModel>() {

    override var viewModel: SearchMusicViewModel = SearchMusicViewModel()

    // Save playlist for add
    var playListId: String = ""

    // Save input search
    private var inputSearch = mutableStateOf("")

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
                    Text(text = stringResource(Res.string.search_musics_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
                val keyboardController = LocalSoftwareKeyboardController.current
                LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    item {
                        OutlinedTextField(
                            value = inputSearch.value,
                            onValueChange = {
                                inputSearch.value = it
                                makeSearchWorking()
                            },
                            modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16(),
                            leadingIcon = @Composable {
                                Icon(
                                    painterResource(Res.drawable.btn_search),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp),
                                )
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            ),
                            placeholder = { Text(text = stringResource(Res.string.search_musics_hint), style = textContentSecond()) },
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            enabled = true,
                            readOnly = false
                        )
                    }

                    if (viewModel.listMusicSearch.isEmpty()) {
                        item {
                            EmptyDataView()
                        }
                    } else {
                        items(viewModel.listMusicSearch.size) { index ->
                            val itemMusic = viewModel.listMusicSearch[index]
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
                                        Column(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 16.dp)) {
                                            Text(text = itemMusic.name, style = textContentPrimary(), modifier = Modifier.padding(bottom = 8.dp))
                                            Text(text = itemMusic.singerModel?.name.toString(), style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                        }

                                        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                                            // Add
                                            IconButton(
                                                onClick = {
                                                    handleAddPlaylist(itemMusic.id)
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

                                            // Fav
                                            IconButton(
                                                onClick = {

                                                },
                                                modifier = Modifier.size(45.dp).padding(start = 16.dp),
                                                content = {
                                                    // Specify the icon using the icon parameter
                                                    androidx.compose.material.Icon(
                                                        painter = painterResource(Res.drawable.btn_fav_unactive),
                                                        contentDescription = null,
                                                        modifier = Modifier.size(35.dp),
                                                        tint = Color.Unspecified,
                                                    )
                                                }
                                            )
                                        }
                                    }
                                })
                        }
                    }
                }
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listAllMusic.isEmpty()) {
            viewModel.loadAllMusic()
        }
    }

    override fun onDisposedScreen() {
    }

    /**
     * Make search
     */
    private fun makeSearchWorking() {
        viewModel.searchMusicByName(inputSearch = inputSearch.value)
    }

    /**
     * Handle case add playlist
     *
     * @param musicId
     */
    private fun handleAddPlaylist(musicId: String) {
        if (playListId.isNotEmpty()) {
            viewModel.addMusicToPlaylist(playListId, musicId)
        } else {
            // TODO open dialog create playlist
        }
    }
}