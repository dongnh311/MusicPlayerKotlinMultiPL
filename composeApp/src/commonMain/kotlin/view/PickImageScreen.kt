package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import base.BaseScreen
import co.touchlab.kermit.Logger
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberImagePainter
import commonShare.toOkioPath
import model.ImagePickerModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_checked
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import singleton.MusicPlayerSingleton.screenSize
import styles.buttonSize32dp
import styles.iconSize32dp
import utils.exts.pxToDp
import viewModel.PickImageViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 02/04/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class PickImageScreen(private val listImage : MutableList<ImagePickerModel>) : BaseScreen<PickImageViewModel>() {
    override var viewModel: PickImageViewModel = PickImageViewModel()

    var onSelectedImageAvatar : OnSelectedImageAvatar? = null

    interface OnSelectedImageAvatar {
        fun onSelectedImage(imagePickerModel: ImagePickerModel)
    }


    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun makeContentForView() {
        val indexSelectedItem = remember { mutableStateOf<Int>(-1) }

        val isHaveItemSelect = remember { mutableStateOf(false) }

        var widthImage = screenSize.value.first / 3.5
        if (widthImage < 0) widthImage = 150.dp.value.toDouble()

        Scaffold(modifier = Modifier.fillMaxSize().background(Color.Red), topBar = {
            Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Icon(
                    painter = painterResource(Res.drawable.btn_back),
                    contentDescription = "Back",
                    modifier = buttonSize32dp()
                        .clickable {
                            navigator.pop()
                        }.align(Alignment.CenterStart)
                )

                IconButton(modifier = Modifier.align(Alignment.CenterEnd).size(45.dp), onClick = {
                    onSelectedImageAvatar?.onSelectedImage(listImage[indexSelectedItem.value])
                    navigator.pop()
                }, enabled = isHaveItemSelect.value) {
                    Icon(
                        painter = painterResource(Res.drawable.btn_checked),
                        contentDescription = "Done",
                        modifier = iconSize32dp()
                        )
                }

            }
        }, content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top =  it.calculateTopPadding(), start = 16.dp, end = 16.dp).fillMaxSize(),
            ) {

                items(listImage.size) { index ->
                    val itemImage = listImage[index]

                    // Check is select
                    val isSelected = remember { itemImage.isChoose }
                    val colorSelected = remember { mutableStateOf(Color.Gray) }

                    LaunchedEffect(isSelected.value) {
                        colorSelected.value = if (isSelected.value) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    }

                    val painter = if (itemImage.mediaPath.isNotEmpty()) {
                        val path = itemImage.mediaPath.toOkioPath()
                        Logger.e("Path Okio : $path")
                        val imageRequest = ImageRequest { data(path) }
                        rememberImagePainter(imageRequest)
                    } else {
                        painterResource(Res.drawable.avatar_default)
                    }
                    Box(modifier = Modifier.border(2.dp, colorSelected.value, RoundedCornerShape(4.dp))  // add a border (optional)
                        .clickable {
                            // Reset all value
                            for (indexList in 0 until listImage.size) {
                                if (indexList != index) {
                                    listImage[indexList].isChoose.value = false
                                }
                            }
                            itemImage.isChoose.value = !itemImage.isChoose.value
                            isHaveItemSelect.value = itemImage.isChoose.value
                            if (itemImage.isChoose.value) {
                                indexSelectedItem.value = index
                            } else {
                                indexSelectedItem.value = -1
                            }
                    }) {
                        Image(
                            painter = painter,
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,            // crop the image if it's not a square
                            modifier = Modifier
                                .aspectRatio(1f)
                                .size(widthImage.pxToDp())
                                .clip(RoundedCornerShape(4.dp))          // clip to the circle shape

                        )
                    }
                }
            }
        })
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}