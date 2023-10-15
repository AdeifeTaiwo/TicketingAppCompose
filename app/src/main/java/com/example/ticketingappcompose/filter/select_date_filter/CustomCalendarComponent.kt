package com.example.ticketingappcompose.filter

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale




@Composable
fun CustomDaysOfWeeksHeader(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault())
                    .substring(0, 1),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
        }
    }
}




@Composable
fun CustomMonthHeader(
    monthState: MonthState,
    modifier: Modifier = Modifier
) {

    /**
     * Default implementation of month header, shows current month and year, as well as
     * 2 arrows for changing currently showed month
     */

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp)
                .testTag("Decrement")
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)

                .clickable {
                    monthState.currentMonth = monthState.currentMonth.minusMonths(1)

                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous",
            )
        }

        //Month
        Row {

            Text(
                modifier = Modifier.testTag("MonthLabel"),
                text = monthState.currentMonth.month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())
                    .lowercase()
                    .replaceFirstChar { it.titlecase() },
                style = androidx.compose.ui.text.TextStyle(),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = monthState.currentMonth.year.toString(),
                style = androidx.compose.ui.text.TextStyle()
            )
        }

        //next button
        Box(
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp)
                .testTag("Decrement")
                .background(shape = RoundedCornerShape(8.dp), color = Color.Transparent)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    monthState.currentMonth = monthState.currentMonth.minusMonths(1)

                },
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                modifier = Modifier.testTag("Increment"),
                onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
            ) {
                Image(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Previous",
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun <T : DynamicSelectionState> CustomDay(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    selectionColor: Color = Color.Blue,
    currentDayColor: Color = Color.Blue,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .size(100.dp)
            .clickable {
                onClick(date)
                selectionState.onDateSelected(date)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = if (isSelected) BorderStroke(1.dp, currentDayColor) else null,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    color = if (isSelected) selectionColor else Color.Black
                )
            )
        }
    }
}
