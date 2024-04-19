package utils.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import childView.EmptyDataView
import childView.LastItemForListView
import com.seiko.imageloader.rememberImagePainter
import commonShare.formatNumberToMoney
import commonShare.viewTimeByTimestamp
import model.CoinModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.avatar_default
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_buy_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_coin_title
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_discount
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_expire
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_money
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import singleton.MusicPlayerSingleton.loadHeightBottomSheet
import singleton.MusicPlayerSingleton.loadMaxHeightDialog
import singleton.MusicPlayerSingleton.loadMaxWidthDialog
import styles.colorAccountCard
import styles.colorWhite
import styles.paddingAll16
import styles.paddingStart16
import styles.paddingTop16
import styles.paddingTop8
import styles.paddingTop8StartEnd16
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun DialogBuyMoreCoin(
    isShowDialog: MutableState<Boolean>,
    listCoinBuy: MutableList<CoinModel>,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    callBackClickBuy: (CoinModel) -> Unit
) {
    if (isShowDialog.value) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Box (modifier = Modifier.fillMaxWidth().height(loadHeightBottomSheet()).background(colorWhite).border(0.dp, Color.Transparent,
                shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))) {

                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().paddingAll16().background(colorWhite),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                    Text(text = stringResource(Res.string.dialog_buy_coin_title), style = textTittleContent(), modifier = Modifier.fillMaxWidth())

                    LazyColumn(modifier = Modifier.fillMaxSize().paddingTop16(),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                        if (listCoinBuy.isEmpty()) {
                            item {
                                EmptyDataView()
                            }
                        } else {
                            items(listCoinBuy.size) { index ->
                                val itemCoin = listCoinBuy[index]
                                Card(modifier = Modifier
                                    .fillMaxWidth().paddingTop8().clickable(enabled = true) {
                                        isShowDialog.value = false
                                        callBackClickBuy.invoke(itemCoin)
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
                                        Row(modifier = Modifier.fillMaxWidth().paddingAll16(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                                            val painter = if (itemCoin.thumbnail.isNotEmpty()) rememberImagePainter(itemCoin.thumbnail)
                                            else painterResource(Res.drawable.btn_buy_coin)
                                            Image(
                                                painter = painter,
                                                contentDescription = "avatar",
                                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .clip(RectangleShape)                       // clip to the circle shape
                                                    .border(2.dp, Color.Gray, RectangleShape)   // add a border (optional)
                                            )

                                            Column(
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.Center,
                                                modifier = Modifier.paddingStart16()
                                            ) {
                                                Text(
                                                    text = stringResource(Res.string.dialog_buy_coin) + " ${formatNumberToMoney(itemCoin.coin)}",
                                                    style = textContentPrimary(),
                                                    modifier = Modifier.padding(top = 8.dp)
                                                )

                                                val countMountDiscount = itemCoin.money - ((itemCoin.discount / 100) * itemCoin.money)
                                                var textMoney = " ${formatNumberToMoney(countMountDiscount)}"
                                                if (countMountDiscount.toLong() != itemCoin.money) {
                                                    textMoney = " ${formatNumberToMoney(countMountDiscount)} (${formatNumberToMoney(itemCoin.money)})"
                                                }
                                                Text(
                                                    text = stringResource(Res.string.dialog_buy_money) + textMoney,
                                                    style = textContentSecond(),
                                                    modifier = Modifier.padding(top = 8.dp)
                                                )
                                                Text(
                                                    text = stringResource(Res.string.dialog_buy_discount) + " ${formatNumberToMoney(itemCoin.discount, "##0.0")}%" ,
                                                    style = textContentSecond(),
                                                    modifier = Modifier.padding(top = 8.dp)
                                                )

                                                Text(
                                                    text = stringResource(Res.string.dialog_buy_expire) + viewTimeByTimestamp(
                                                        itemCoin.expireAt
                                                    ),
                                                    style = textContentSecond(),
                                                    modifier = Modifier.padding(top = 8.dp)
                                                )
                                            }
                                        }
                                    })
                            }

                            item {
                                LastItemForListView()
                            }
                        }
                    }
                }
            }
        }

    }
}