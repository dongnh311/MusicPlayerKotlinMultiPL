package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import base.BaseScreen
import com.seiko.imageloader.rememberImagePainter
import commonShare.viewTimeByTimestamp
import model.EventModel
import musicplayerkotlinmultipl.composeapp.generated.resources.Res
import musicplayerkotlinmultipl.composeapp.generated.resources.btn_back
import musicplayerkotlinmultipl.composeapp.generated.resources.event_detail_createAt
import musicplayerkotlinmultipl.composeapp.generated.resources.event_detail_expireAt
import musicplayerkotlinmultipl.composeapp.generated.resources.event_detail_title
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
import styles.textTittleContent
import styles.textTittleHome
import viewModel.EventDetailViewModel

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 12/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
class EventDetailScreen: BaseScreen<EventDetailViewModel>() {

    override var viewModel: EventDetailViewModel = EventDetailViewModel()

    // Save event
    var eventModel = EventModel()

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
                        modifier = buttonSize32dp()
                            .clickable {
                                navigator.pop()
                            }
                    )
                    Text(text = stringResource(Res.string.event_detail_title), style = textTittleHome(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.height(45.dp))
                }
            }) {
                Column(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding())) {
                    if (eventModel.image != null) {
                        val painter =  rememberImagePainter(eventModel.image!!)
                        Card(modifier = Modifier
                            .fillMaxWidth().paddingTop8StartEnd16().clickable(enabled = true) {

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
                                Image(
                                    painter = painter,
                                    contentDescription = "image",
                                    modifier = Modifier.fillMaxWidth().heightIn(max = 200.dp),
                                    contentScale = ContentScale.FillWidth,
                                )
                            })
                    }

                    // Title
                    Text(text = eventModel.title, style = textTittleContent(), modifier = Modifier.fillMaxWidth().paddingTop16StartEnd16())

                    // Date time
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = stringResource(Res.string.event_detail_createAt) + viewTimeByTimestamp(eventModel.createAt),
                            style = textContentSecond(), textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16().weight(1f))

                        var textExpire = "N/A"
                        if (eventModel.expireAt != null) {
                            textExpire = viewTimeByTimestamp(eventModel.expireAt!!)
                        }
                        Text(text = stringResource(Res.string.event_detail_expireAt) + textExpire,
                            style = textContentSecond(), textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16().weight(1f))
                    }

                    Text(text = eventModel.content, style = textContentPrimary(), modifier = Modifier.fillMaxWidth().paddingTop8StartEnd16())
                }
        }
    }

    override fun onStartedScreen() {
    }

    override fun onDisposedScreen() {
    }
}