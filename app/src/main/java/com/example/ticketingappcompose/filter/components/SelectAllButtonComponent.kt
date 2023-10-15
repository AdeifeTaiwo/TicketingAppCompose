package com.example.ticketingappcompose.filter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectAllButtonComponent(
    hasSelectedAll: Boolean = false,
    onClickSelectAllButton: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {

        IconButton(modifier = Modifier.requiredWidth(80.dp), onClick = {
            onClickSelectAllButton()
        }) {

            Text(
                modifier = Modifier.padding(top = 4.dp),
                textAlign = TextAlign.Center,
                text = if (hasSelectedAll) "Deselect All" else "Select All",
                style = TextStyle(
                    color = Color(0xFF098EEF),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
            )
        }
    }
}
