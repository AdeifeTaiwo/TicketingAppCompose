package com.example.ticketingappcompose.filter.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SaveFilterButtonComponent(
    onPrimaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    // Primary Button
    Button(
        onClick = { onPrimaryButtonClick() },
        modifier = modifier
            .padding(20.dp)
            .width(250.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF098EEF)
        )
    ) {
        Text(text = "SAVE FILTER")
    }
}