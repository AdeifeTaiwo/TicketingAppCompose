package com.example.ticketingappcompose.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.R
import java.util.Locale

@Composable
fun HomeScreenItem() {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {

        homeScreenTicketDetailContent(modifier = Modifier.wrapContentSize()) {}

        Spacer(modifier = Modifier.height(10.dp))

        homeScreenTicketDetailContent(modifier = Modifier.wrapContentSize()) {}

        Spacer(modifier = Modifier.height(10.dp))
        homeScreenTicketDetailContent(modifier = Modifier.wrapContentSize()) {}

        Spacer(modifier = Modifier.height(10.dp))
        homeScreenTicketDetailContent(modifier = Modifier.wrapContentSize()) {


        }
    }
}


@Composable
fun homeScreenTicketDetailContent(
    modifier: Modifier = Modifier,
    onClickDropDown: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .graphicsLayer {
            }
            .wrapContentSize()
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .semantics { selected = true },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, color = Color(0XFFD5D5D5)),
        elevation = CardDefaults.outlinedCardElevation(
            pressedElevation = 8.dp
        )

    ) {

        var isExpanded by remember { mutableStateOf(false) }
        val density = LocalDensity.current

        Column {

            Row(
                modifier = modifier
                    .height(130.dp)
                    .padding(start = 8.dp, end = 8.dp),

                ) {

                Row(
                    modifier = Modifier
                        .weight(.8f),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    dateContent(modifier = Modifier.weight(.2f))
                    //Calendar Column
                    Spacer(modifier = Modifier.width(14.dp))
                    //Ticket details Column
                    ticketDetailsContent(modifier = Modifier.weight(.7f))

                }

                ballWithDropDownIcon(modifier = Modifier.weight(.2f)) {
                    isExpanded = it
                    onClickDropDown(it)
                }
            }
            AnimatedVisibility(visible = isExpanded,
                enter = slideInVertically(
                    animationSpec = SpringSpec(Spring.DampingRatioLowBouncy)
                ) {
                    // Slide in from 40 dp from the top.
                    with(density) { -100.dp.roundToPx() }
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()) {

                Column(modifier = Modifier.wrapContentSize()) {

                    Divider(thickness = .5.dp, color = Color.LightGray)

                    DrawFirstParallelogram(modifier = Modifier.fillMaxWidth(.8f))
                    sectionRowWithArrowIcon()

                    Divider(thickness = .5.dp, color = Color.LightGray)
                    DrawFirstParallelogram(modifier = Modifier.fillMaxWidth(.8f))
                    sectionRowWithArrowIcon()
                }

            }
        }
    }
}


@Composable
fun ticketDetailsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(250.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = "Los Angeles Lakers vs. Los Angeles Clippers",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0XFF4E4E4E), fontWeight = FontWeight.W700
            ),
            lineHeight = 20.sp,
            maxLines = 2
        )//end text

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_icon),
                contentDescription = ""
            )//end image
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "Crypto.com Arena, 7PM",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color(0XFF797979),
                    fontWeight = FontWeight.W400
                )
            )//end text
        } //end row

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "2 of 4 Sold @ $1,000",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color(0XFF098EEF),
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}

@Composable
fun dateContent(modifier: Modifier = Modifier) {
    //Calendar Column
    Column(
        modifier = modifier
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aug".uppercase(Locale.ROOT),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0XFF4E4E4E),
                fontWeight = FontWeight.W700
            )
        )
        Text(
            text = "13".uppercase(Locale.ROOT),
            style = TextStyle(
                fontSize = 38.sp,
                color = Color(0XFF4E4E4E),
                fontWeight = FontWeight.W700
            )
        )
        Text(
            text = "Wed".uppercase(Locale.ROOT),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0XFF098EEF),
                fontWeight = FontWeight.W600
            )
        )

    }

}

@Composable
fun ballWithDropDownIcon(modifier: Modifier = Modifier, onExpand: (Boolean) -> Unit) {

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {


        Image(
            modifier = Modifier
                .padding(top = 15.dp, end = 10.dp),
            painter = painterResource(id = R.drawable.ic_ball),
            contentDescription = ""
        )//end image

        Image(
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    isExpanded = !isExpanded
                    onExpand(isExpanded)
                }
                .padding(top = 4.dp, end = 15.dp),
            painter = painterResource(id = if (!isExpanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up),
            contentDescription = "",
        )
    }
}

@Composable
fun sectionRowWithArrowIcon() {
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text(
            text = "Section 48, Row 03 Seats 10-13",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0XFF4E4E4E), fontWeight = FontWeight.W600
            ),
            lineHeight = 20.sp,
            maxLines = 2
        )//end text

        Image(
            modifier = Modifier
                .size(24.dp)
                .padding(top = 4.dp, end = 15.dp),
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = "",
        )
    }
}


@Composable
fun homeScreenTicketDetailWithNoSectionRow() {
   
        Column {
            homeScreenTicketDetailContent(modifier = Modifier.weight(1f)) {
            }
        }
}


@Preview
@Composable
fun PreviewHomeScreenTicketDetailContent() {
    Surface(modifier = Modifier.height(150.dp)) {
        homeScreenTicketDetailWithNoSectionRow()
    }

}

@Preview
@Composable
fun PreviewHomeScreenItem() {
    Surface {
        HomeScreenItem()
    }

}