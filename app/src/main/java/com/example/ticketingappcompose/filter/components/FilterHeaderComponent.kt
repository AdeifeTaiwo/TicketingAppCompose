package com.example.ticketingappcompose.filter.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterHeaderComponent(
    headerText: String,
    onDismiss: () -> Unit,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Reset Button
        Text(
            text = "Reset",
            color = Color.Blue,
            fontSize = 14.sp
        )

        Text(
            text = headerText,
            fontSize = 16.sp
        )

        // Close Button and Filter Text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = "Close Icon",
                    tint = Color(0xFF098EEF)
                )
            }
        }
    }
}


@Composable
fun FilterHeaderWithBackArrow(
    headerText: String,
    onDismiss: () -> Unit,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Reset Button
        Icon(
            modifier = Modifier.height(24.dp).width(16.dp),
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "",
            tint = Color(0xFF098EEF)
        )

        Text(
            text = headerText,
            fontSize = 16.sp
        )


        // Close Button and Filter Text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color(0xFF098EEF)
                )
            }
        }
    }
}