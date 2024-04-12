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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import base.BaseScreen
import childView.EmptyDataView
import co.touchlab.kermit.Logger
import com.seiko.imageloader.rememberImagePainter
import commonShare.CallBackResultPermission
import commonShare.loadPermissionControl
import commonShare.loadTimestamp
import commonShare.viewTimeByTimestamp
import const.DATE_LOCAL_FORMAT_TYPE_COMMON_5
import const.LIMIT_MUSIC_ON_PLAY_LIST
import const.LIMIT_PLAY_LIST
import const.LIMIT_PLAY_LIST_FREE
import const.PERMISSION_GRAND
import model.ImagePickerModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_add
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_cancel
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_edit
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_fav_unactive
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_google
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.home_ranking_empty
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_empty_data
import musicplayerkotlinmultipl.composeapp.generated.resources.new_music_title
import musicplayerkotlinmultipl.composeapp.generated.resources.pick_image_fail_permission
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_btn_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_btn_edit
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_confirm_delete
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_count_musics
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_count_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_createAt
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_create_new_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_limit_pl
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_tab_detail
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_tab_playlist
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_title
import musicplayerkotlinmultipl.composeapp.generated.resources.playlist_update
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonColorsCancel
import styles.buttonColorsGoogle
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16
import styles.paddingTop4
import styles.paddingTop8
import styles.paddingTop8StartEnd16
import styles.textButton
import styles.textContentPrimary
import styles.textContentSecond
import styles.textContentThree
import styles.textTittleContent
import styles.textTittleHome
import utils.dialogs.DialogCreatePlaylist
import utils.dialogs.ShowDialogConfirm
import viewModel.PlaylistViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PlaylistScreen: BaseScreen<PlaylistViewModel>() {
    override var viewModel: PlaylistViewModel = PlaylistViewModel()

    // Check to open dialog
    private val isOpenDialogInputName = mutableStateOf(false)

    // Enable edit
    private val selectedIndex = mutableStateOf(-1)

    // Delete
    private val isDeletePlaylist = mutableStateOf(false)

    // Save input name
    private val inputName = mutableStateOf("")

    private val isShowDialogFailPermission = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        var tabIndex by remember { mutableStateOf(0) }
        val listTab = arrayListOf(stringResource(Res.string.playlist_tab_playlist), stringResource(Res.string.playlist_tab_detail))

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
                    Text(text = stringResource(Res.string.playlist_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
                Box(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()), contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                        TabRow(selectedTabIndex = tabIndex) {
                            listTab.forEachIndexed { index, title ->
                                Tab(text = { Text(title) },
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index }
                                )
                            }
                        }
                        when (tabIndex) {
                            0 -> {
                                showPlaylist()
                            }
                            1 -> {

                            }
                        }
                    }
                }
        }

        // Check to create new playlist
        if (isOpenDialogInputName.value) {
            DialogCreatePlaylist(
                isOpenDialogInputName,
                inputName
            ) { _ ->
                isOpenDialogInputName.value = false
                if (selectedIndex.value == -1) {
                    viewModel.createNewPlayList(inputName.value)
                } else {
                    // Update name
                    try {
                        val item = viewModel.listPlayList[selectedIndex.value]
                        item.name = inputName.value
                        item.updateAt = loadTimestamp().toLong()
                        viewModel.updateInformationPlaylist(item)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    selectedIndex.value = -1
                }

                inputName.value = ""
            }
        }

        // Is delete
        if (isDeletePlaylist.value) {
            ShowDialogConfirm(
                isDeletePlaylist,
                title = "",
                content = stringResource(Res.string.playlist_confirm_delete),
                textButtonLeft = stringResource(Res.string.btn_cancel),
                textButtonRight = stringResource(Res.string.btn_delete),
                callBackLeft = {
                    isDeletePlaylist.value = false
                },
                callBackRight = {
                    isDeletePlaylist.value = false
                    try {
                        val item = viewModel.listPlayList[selectedIndex.value]
                        viewModel.deletePlaylist(item)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    selectedIndex.value = -1
                }
            )
        }

        // Show dialog need permission
        if (isShowDialogFailPermission.value) {
            showDialogMessage(
                title = "",
                content = stringResource(Res.string.pick_image_fail_permission),
                callBackOk = {
                    isShowDialogFailPermission.value = false
                    // TODO : Goto setting
                }
            )
        }
    }

    override fun onStartedScreen() {
        if (viewModel.listPlayList.isEmpty()) {
            viewModel.loadListPlaylist()
        }
    }

    override fun onDisposedScreen() {
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showPlaylist() {
        if (viewModel.listPlayList.isEmpty()) {
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                val painter = painterResource(Res.drawable.btn_add)
                IconButton(
                    onClick = {
                        // Create new play list
                        isOpenDialogInputName.value = true
                    },
                    modifier = Modifier.size(50.dp).paddingTop16().aspectRatio(1f),
                    content = @Composable {
                        // Specify the icon using the icon parameter
                        androidx.compose.material.Icon(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp),
                            tint = Color.Unspecified,
                        )
                    }
                )
                Text(text = stringResource(Res.string.playlist_create_new_pl), modifier = Modifier.fillMaxWidth()
                    .paddingTop8(), textAlign = TextAlign.Center)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                item {
                    Text(text = "*" + stringResource(Res.string.playlist_limit_pl), textAlign = TextAlign.Start,
                        modifier = Modifier.paddingTop8StartEnd16().fillMaxWidth(), style = textContentPrimary()
                    )
                }

                item {
                    Row(modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = stringResource(Res.string.playlist_count_playlist) + " ${viewModel.listPlayList.size}/$LIMIT_PLAY_LIST", textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f), style = textContentPrimary()
                        )

                        Button(onClick = {
                            // TODO Create new pl
                            selectedIndex.value = -1
                            isOpenDialogInputName.value = false
                        }, modifier = Modifier, shape = RoundedCornerShape(35.dp), colors = buttonColorsGoogle()) {
                            androidx.compose.material.Icon(
                                painter = painterResource(Res.drawable.btn_add),
                                modifier = Modifier.size(20.dp),
                                contentDescription = "drawable icons",
                                tint = Color.Unspecified
                            )
                            Text(
                                text = stringResource(Res.string.playlist_create_new_pl),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = textButton(),
                                modifier = Modifier.padding(start = 8.dp)
                                //default icon width = 24.dp
                            )
                        }
                    }
                }

                // Divider
                item {
                    Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth().paddingTop8()) // add divider here
                }

                // Playlist
                items(viewModel.listPlayList.size) {index ->
                    Card(modifier = Modifier
                        .fillMaxWidth().paddingTop8StartEnd16().clickable(enabled = true) {
                            // TODO : open detail
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
                                val itemPlaylist = viewModel.listPlayList[index]
                                if (itemPlaylist.thumbnail.isNotEmpty()) {
                                    Image(
                                        painter = rememberImagePainter(itemPlaylist.thumbnail),
                                        contentDescription = "avatar",
                                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                        modifier = Modifier
                                            .size(100.dp)
                                            .aspectRatio(1f)
                                            .clip(RectangleShape)                       // clip to the circle shape
                                            .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                    )
                                } else {
                                    androidx.compose.material3.IconButton(
                                        onClick = {
                                            // TODO Edit thumb
                                            selectedIndex.value = index
                                            handleUpdateThumbnail()
                                        },
                                        modifier = Modifier.size(100.dp).clip(RectangleShape)                       // clip to the circle shape
                                            .border(2.dp, Color.Gray, RectangleShape),  // add a border (optional),
                                        content = {
                                            // Specify the icon using the icon parameter
                                            androidx.compose.material.Icon(
                                                painter = painterResource(Res.drawable.btn_add),
                                                contentDescription = null,
                                                modifier = Modifier.size(32.dp),
                                                tint = Color.Unspecified,
                                            )
                                            Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                        }
                                    )
                                }

                                // Add another single item
                                Column(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 16.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = itemPlaylist.name, style = textContentPrimary(), modifier = Modifier)

                                        androidx.compose.material3.IconButton(
                                            onClick = {
                                                // Edit name
                                                inputName.value = itemPlaylist.name
                                                selectedIndex.value = index
                                                isOpenDialogInputName.value = true
                                            },
                                            modifier = Modifier.size(30.dp),
                                            content = {
                                                // Specify the icon using the icon parameter
                                                androidx.compose.material.Icon(
                                                    painter = painterResource(Res.drawable.btn_edit),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp),
                                                    tint = Color.Unspecified,
                                                )
                                                Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                            }
                                        )
                                    }
                                    Row(modifier = Modifier.fillMaxWidth().paddingTop4(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = stringResource(Res.string.playlist_count_musics) + " ${itemPlaylist.listMusicsId.size}" + "/$LIMIT_MUSIC_ON_PLAY_LIST",
                                            style = textContentSecond(), modifier = Modifier)
                                    }

                                    Row(modifier = Modifier.fillMaxWidth().paddingTop8(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = stringResource(Res.string.playlist_createAt) + " ${viewTimeByTimestamp(itemPlaylist.createAt, DATE_LOCAL_FORMAT_TYPE_COMMON_5)}",
                                            style = textContentThree(), modifier = Modifier.weight(1f))
                                    }

                                    Row(modifier = Modifier.fillMaxWidth().paddingTop8(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = stringResource(Res.string.playlist_update) + " ${viewTimeByTimestamp(itemPlaylist.updateAt, DATE_LOCAL_FORMAT_TYPE_COMMON_5)}",
                                            style = textContentThree(), modifier = Modifier.weight(1f))
                                    }

                                }

                                // Button delete
                                androidx.compose.material3.IconButton(
                                    onClick = {
                                        // Delete
                                        selectedIndex.value = index
                                        isDeletePlaylist.value = true
                                    },
                                    modifier = Modifier.size(45.dp),
                                    content = {
                                        // Specify the icon using the icon parameter
                                        androidx.compose.material.Icon(
                                            painter = painterResource(Res.drawable.btn_delete),
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp),
                                            tint = Color.Unspecified,
                                        )
                                        Spacer(modifier = Modifier.width(4.dp)) // Adjust spacing
                                    }
                                )

                            }
                        })
                }
            }
        }
    }

    /**
     * Handle to load pick image for thumb
     */
    private fun handleUpdateThumbnail() {
        val permissionControl = loadPermissionControl()
        permissionControl.callBackResultPermission = object : CallBackResultPermission {
            override fun onResultPermission(result: Int) {
                if (result == PERMISSION_GRAND) {
                    val listImages = permissionControl.loadAllImageMedia()
                    Logger.e("Load image on device : ${listImages.size}")
                } else {
                    Logger.e("Fail to request permission")
                    isShowDialogFailPermission.value = true
                }
            }
        }

        if (permissionControl.checkPermissionStorage()) {
            val listImages = permissionControl.loadAllImageMedia()
            Logger.e("Load image on device : ${listImages.size}")
            val pickImageScreen = PickImageScreen(listImages)
            navigator.push(pickImageScreen)
            pickImageScreen.onSelectedImageAvatar = object : PickImageScreen.OnSelectedImageAvatar {
                override fun onSelectedImage(imagePickerModel: ImagePickerModel) {
                    Logger.e("Callback item image selected : ${imagePickerModel.mediaPath}")
                    try {
                        val item = viewModel.listPlayList[selectedIndex.value]
                        item.thumbnail = imagePickerModel.mediaPath
                        viewModel.uploadImageFileToThumb(item)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            permissionControl.requestPermissionStorage()
            Logger.e("Request permission")
        }
    }
}