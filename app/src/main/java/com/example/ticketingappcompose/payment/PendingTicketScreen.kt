package com.example.ticketingappcompose.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.R
import com.example.ticketingappcompose.home.ballWithDropDownIcon
import com.example.ticketingappcompose.home.dateContent


@Composable
fun PendingTicketScreen() {

   Row {


        dateContent(modifier = Modifier.weight(.2f))
        //Calendar Column
        Spacer(modifier = Modifier.width(14.dp))
        //Ticket details Column
        com.example.ticketingappcompose.home.ticketDetailsContent(modifier = Modifier.weight(.7f))

        ballWithDropDownIcon(modifier = Modifier.weight(.2f)) {

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
                text = "Crypto.com Arenafskjlfjlsjljfjlfjlajlf, 7PM",
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

@Preview(showBackground = true)
@Composable
fun PreviewPendingTicketScreen(){
    PendingTicketScreen()
}
