package com.example.ticketingappcompose.payment

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.ui.theme.TicketingAppComposeTheme
import kotlinx.coroutines.launch
import com.example.ticketingappcompose.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {


    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) { 3 }
    val tabItems = tabRowItems

    Column(modifier = Modifier.fillMaxSize()) {

        //Payment header Text
        Row(modifier = modifier.padding(top = 30.dp)) {
            Text(
                modifier = modifier.padding(start = 20.dp),
                text = "Payments",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            )
        }

        Spacer(Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(
                containerColor = Color.Transparent.copy(0.1f),//To separate it from background
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(16.dp)),//Consistent look
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    CustomIndicator(tabPositions = tabPositions, pagerState = pagerState)

                }
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    Tab(
                        modifier = Modifier
                            .clip(CircleShape)//Round shape for each item
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                            .height(36.dp)
                            .background(color = if (pagerState.currentPage == index) Color.White else Color.Transparent),
                        text = { Text(text = item.title, color = Color.Black) },
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
            ) {
                tabRowItems[pagerState.currentPage].screen()
            }
        }
    }


}


@Composable
fun PaymentPendingScreen(){

    Column(modifier = Modifier.fillMaxSize()) {

        nextPaymentCardDetailComponent(
            containerColor = Color(0XFFD7D7D7),
            textColor = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        ticketSaleProceedsItem()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentBalanceScreen(modifier: Modifier = Modifier) {


    Column(modifier = Modifier.fillMaxSize()) {


        nextPaymentCardDetailComponent()

        Spacer(modifier = Modifier.height(20.dp))

        lastDaysDropDown(modifier = Modifier)

        repeat(5){
            ticketSaleProceedsItem()
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier.fillMaxWidth().padding(10.dp), 
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {

            Text(
                modifier = Modifier
                    .weight(.55f)
                    .fillMaxWidth(), text = "Previous Balance",
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0XFF120D26)
                ),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .weight(.45f)
                    .fillMaxWidth(), text = "$0.00",
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0XFF120D26)
                ),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Composable
fun lastDaysDropDown(
    modifier: Modifier = Modifier
){
    Box(modifier = Modifier
        .height(38.dp)
        .padding(start = 16.dp)
        .border(border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))

    ) {
        Row(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Text(text = "Last 30 days", modifier.align(Alignment.CenterVertically) )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(modifier = Modifier.padding(top = 4.dp), painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = "")
        }

    }
}

@Composable
fun nextPaymentCardDetailComponent(
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0XFF098EEF),
    textColor: Color = Color.White
){

    Spacer(modifier = Modifier.height(16.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(2.dp)),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Row(modifier = modifier.padding(top = 20.dp)) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = "Next Payments",
                style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 18.sp, fontWeight = FontWeight.W500, color = textColor)
            )
        }

        Row(modifier = modifier.padding(top = 0.dp)) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = "$1,640.00",
                style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 18.sp, fontWeight = FontWeight.W500, color = textColor)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(modifier = Modifier.padding(horizontal = 10.dp),thickness = 1.dp, color = Color.White)

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = modifier.padding(top = 0.dp)) {
            Text(
                modifier = modifier.padding(start = 16.dp),
                text = "Estimated date: September 5, 2023",
                style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 18.sp, fontWeight = FontWeight.W400, color = textColor)
            )
        }

    }
}

@Composable
fun ticketSaleProceedsItem(
    modifier: Modifier = Modifier
){

    Spacer(modifier = Modifier.height(16.dp))
    Column() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.weight(.9f),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier.weight(.2f), text = "13/24/23",
                    style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0XFF797979)
                    )
                )
                Text(
                    modifier = Modifier
                        .weight(.55f)
                        .fillMaxWidth(), text = "Ticket Sales Proceeds",
                    style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0XFF120D26)
                    ),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )

                Column(
                    modifier = Modifier
                        .weight(.35f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = modifier.padding(top = 0.dp)) {
                        Text(
                            modifier = modifier
                                .padding(start = 0.dp)
                                .fillMaxWidth(),
                            text = "+$8255.00",
                            textAlign = TextAlign.Right,
                            style = TextStyle(
                                fontStyle = FontStyle.Normal,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                color = Color(0XFF098EEF)
                            )
                        )
                    }

                    Row(modifier = modifier.padding(top = 0.dp)) {
                        Text(
                            modifier = modifier
                                .padding(start = 0.dp)
                                .fillMaxWidth(),
                            text = "$1,640.00",
                            textAlign = TextAlign.Right,
                            style = TextStyle(
                                fontStyle = FontStyle.Normal,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.DarkGray
                            )
                        )
                    }
                }
            }

            Icon(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .weight(.1f),
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = ""
            )

        }

        Divider(modifier = Modifier.padding(horizontal = 10.dp),thickness = .7.dp, color = Color.LightGray)

    }

}

data class PaymentBalanceTabItem(
    val title: String,
    val screen: @Composable () -> Unit
)

val tabRowItems = listOf<PaymentBalanceTabItem>(
    PaymentBalanceTabItem("Balance", screen = { PaymentBalanceScreen() }),
    PaymentBalanceTabItem("Pending", {PaymentPendingScreen() })
)


//You can alaso copy this as it is

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition =
        updateTransition(pagerState.currentPage, label = "")//Do transition of current page
    val indicatorStart by transition.animateDp(//Indicator start transition animation
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)//Using spring
            } else {
                spring(dampingRatio = 1f, stiffness = 100f)//Change stiffness according to your need
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(//Indicator end transition animation
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 100f)//Or you can change your anim here
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(//Using a whole box around the Tab
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(color = Color.Transparent)

            .padding(5.dp)
    ) {

    }//You can also add a background, but then also use zIndex
}


@Preview(showBackground = true)
@Composable
fun PreviewPaymentBalanceScreen() {
    TicketingAppComposeTheme {
        PaymentScreen()
    }
}