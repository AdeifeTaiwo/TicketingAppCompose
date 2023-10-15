package com.example.ticketingappcompose.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticketingappcompose.filter.components.FilterHeaderComponent
import io.github.boguszpawlowski.composecalendar.SelectableCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun StartDateFilterDialog(
    onDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onPrimaryButtonClick: () -> Unit
) {

    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            ) {

                var selectedDate by remember { mutableStateOf("") }

                FilterHeaderComponent(headerText = "Start Date") {
                    onDismiss()
                }

                Spacer(modifier = Modifier.height(10.dp))

                SelectableCalendar(
                    monthHeader = { CustomMonthHeader(monthState = it) },
                    daysOfWeekHeader = { CustomDaysOfWeeksHeader(daysOfWeek = it) },
                    dayContent = {
                        CustomDay(state = it) { selectedDay ->
                            selectedDate = selectedDay.toString()
                        }
                    }
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(text = selectedDate)
                }

                // Primary Button
                Button(
                    onClick = { onPrimaryButtonClick() },
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp)
                        .height(48.dp)
                ) {
                    Text(text = "SAVE FILTER")
                }
            }
        }) {

    }
}





@Preview
@Composable
fun FilterModalPreview() {
    StartDateFilterDialog(
        onDismiss = { false },
        onResetClick = {
            // Handle Reset click
        },
        onPrimaryButtonClick = {
            // Handle Primary Button click
        }
    )
}
