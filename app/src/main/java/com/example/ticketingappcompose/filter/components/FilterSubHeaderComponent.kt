package com.example.ticketingappcompose.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterSubHeaderComponent(text: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.W400
            )
        )
    }
}