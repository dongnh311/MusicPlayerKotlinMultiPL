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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import base.BaseScreen
import childView.EmptyDataView
import com.seiko.imageloader.rememberImagePainter
import commonShare.formatNumberToMoney
import commonShare.loadTimestamp
import commonShare.viewTimeByTimestamp
import const.PAYMENT_TYPE_VIP
import model.UserModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_buy_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_buy_more
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_number_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_tab_buy
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_tab_payment
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_title
import musicplayerkotlinmultipl.composeapp.generated.resources.coins_total_available
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_discount
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_buy_money
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_vip_bonus
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_vip_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_vip_day
import musicplayerkotlinmultipl.composeapp.generated.resources.dialog_vip_day_expire
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_max_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_supper_vip
import musicplayerkotlinmultipl.composeapp.generated.resources.icon_coin_vip
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import styles.buttonColorsGoogle
import styles.buttonSize32dp
import styles.colorAccountCard
import styles.colorPrimaryBackground
import styles.paddingTop16StartEnd16
import styles.paddingTop8
import styles.paddingTop8StartEnd16
import styles.textButton
import styles.textContentPrimary
import styles.textContentSecond
import styles.textTittleContent
import styles.textTittleHome
import utils.dialogs.DialogBuyMoreCoin
import viewModel.CoinsViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 15/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class CoinsScreen : BaseScreen<CoinsViewModel>() {
    override var viewModel: CoinsViewModel = CoinsViewModel()

    // Save user local
    var userModel : UserModel = UserModel()

    // State
    private val isOpenDialogBuyCoin = mutableStateOf(false)

    @OptIn(ExperimentalResourceApi::class,
        ExperimentalMaterial3Api::class
    )
    @Composable
    override fun makeContentForView() {
        var tabIndex by remember { mutableStateOf(0) }

        val listTab = arrayListOf(stringResource(Res.string.coins_tab_buy), stringResource(Res.string.coins_tab_payment))

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
                    Text(text = stringResource(Res.string.coins_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding()), contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    Row(modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16(),
                        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {

                        Text(text = stringResource(Res.string.coins_total_available) + " ${formatNumberToMoney(userModel.coin)}",
                            style = textTittleContent(), modifier = Modifier.weight(1f))

                        // Create playlist more
                        Button(onClick = {
                            isOpenDialogBuyCoin.value = true
                        }, modifier = Modifier, shape = RoundedCornerShape(35.dp), colors = buttonColorsGoogle()) {
                            androidx.compose.material.Icon(
                                painter = painterResource(Res.drawable.btn_buy_coin),
                                modifier = Modifier.size(20.dp),
                                contentDescription = "drawable icons",
                                tint = Color.Unspecified
                            )
                            Text(
                                text = stringResource(Res.string.coins_buy_more),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = textButton(),
                                modifier = Modifier.padding(start = 8.dp)
                                //default icon width = 24.dp
                            )
                        }
                    }

                    TabRow(selectedTabIndex = tabIndex, modifier = Modifier.fillMaxWidth().paddingTop8()) {
                        listTab.forEachIndexed { index, title ->
                            Tab(text = { Text(title) },
                                selected = tabIndex == index,
                                onClick = { tabIndex = index }
                            )
                        }
                    }
                    when (tabIndex) {
                        0 -> {
                            showTabCoin(it.calculateBottomPadding())
                        }
                        1 -> {
                            showTabCoinUsed(it.calculateBottomPadding())
                        }
                    }
                }
            }
        }

        // Open dialog buy coin
        val stateBottomSheet = rememberModalBottomSheetState()
        if (isOpenDialogBuyCoin.value) {
            DialogBuyMoreCoin(
                isOpenDialogBuyCoin,
                listCoinBuy = viewModel.listCoinsToBuy.toMutableList(),
                sheetState = stateBottomSheet,
                onDismiss = {
                    isOpenDialogBuyCoin.value = false
                }
            ) {
                viewModel.buyCoinForUser(userModel, it)
            }
        }
    }

    override fun onStartedScreen() {
        viewModel.loadListBuyCoinHistory()
        viewModel.loadListCoinUsed()
    }

    override fun onDisposedScreen() {
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showTabCoin(paddingBottomDp: Dp) {
        if (viewModel.listCoinBuyHistory.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = paddingBottomDp), contentAlignment = Alignment.Center) {
                EmptyDataView()
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = paddingBottomDp),
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                items(viewModel.listCoinBuyHistory.size) { index ->
                    val itemCoinHistory = viewModel.listCoinBuyHistory[index]
                    itemCoinHistory.coinModel?.let {coinModel ->
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
                                    val painter = if (coinModel.thumbnail.isNotEmpty()) {
                                        rememberImagePainter(coinModel.thumbnail)
                                    } else painterResource(Res.drawable.btn_buy_coin)
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
                                        Text(text = stringResource(Res.string.coins_number_coin) + " ${formatNumberToMoney(coinModel.coin)}", style = textContentPrimary(), modifier = Modifier.padding(bottom = 0.dp))
                                        val discount = (coinModel.money * (itemCoinHistory.discount /100))
                                        Text(text = stringResource(Res.string.dialog_buy_money) +
                                            " ${formatNumberToMoney(coinModel.money)}", style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                        Text(text = stringResource(Res.string.dialog_buy_discount) +
                                                " ${formatNumberToMoney(discount)}", style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                        Text(text = viewTimeByTimestamp(itemCoinHistory.createAt), style = textContentSecond(), modifier = Modifier.padding(top = 8.dp))
                                    }
                                }
                            })
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun showTabCoinUsed(paddingBottomDp: Dp) {

        if (viewModel.listCoinUsedHistory.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = paddingBottomDp), contentAlignment = Alignment.Center) {
                EmptyDataView()
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = paddingBottomDp),
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {

                items(viewModel.listCoinUsedHistory.size) { index ->
                    val itemCoinUsed = viewModel.listCoinUsedHistory[index]
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
                                if (itemCoinUsed.typeOfItem == PAYMENT_TYPE_VIP) {
                                    val itemVip = viewModel.listItemVip.find { item -> item.id == itemCoinUsed.itemId }
                                    itemVip?.let {
                                        val painter = when (itemCoinUsed.itemId) {
                                            "1" -> {
                                                painterResource(Res.drawable.icon_coin)
                                            }
                                            "2" -> {
                                                painterResource(Res.drawable.icon_coin_vip)
                                            }
                                            "3" -> {
                                                painterResource(Res.drawable.icon_coin_supper_vip)
                                            }
                                            "4" -> {
                                                painterResource(Res.drawable.icon_coin_max_vip)
                                            }
                                            else -> {
                                                painterResource(Res.drawable.icon_coin)
                                            }
                                        }

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
                                            // Name
                                            androidx.compose.material3.Text(
                                                text = itemVip.name,
                                                style = textContentPrimary(),
                                                modifier = Modifier.padding(top = 8.dp)
                                            )

                                            // Coin
                                            androidx.compose.material3.Text(
                                                text = stringResource(Res.string.dialog_vip_coin) + " ${
                                                    formatNumberToMoney(
                                                        itemVip.coinNeedToPay
                                                    )
                                                }",
                                                style = textContentSecond(),
                                                modifier = Modifier.padding(top = 8.dp)
                                            )

                                            // Day
                                            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                                                androidx.compose.material3.Text(
                                                    text = stringResource(Res.string.dialog_vip_day) + " ${itemVip.dayToExpire} day.",
                                                    style = textContentSecond(),
                                                    modifier = Modifier.weight(1f)
                                                )
                                                androidx.compose.material3.Text(
                                                    text = stringResource(Res.string.dialog_vip_bonus) + " ${itemVip.bonus} day.",
                                                    style = textContentSecond(),
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }

                                            val time = loadTimestamp().toLong() + ((itemVip.dayToExpire + itemVip.bonus) * 86400)
                                            androidx.compose.material3.Text(
                                                text = stringResource(Res.string.dialog_vip_day_expire) + viewTimeByTimestamp(
                                                    time
                                                ),
                                                style = textContentSecond(),
                                                modifier = Modifier.padding(top = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        })
                }

            }
        }
    }
}