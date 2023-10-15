package com.example.ticketingappcompose.filter.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.filter.custom_filter_2.CustomDialogViewModel2
import com.example.ticketingappcompose.filter.custom_filter_2.IFilterType
import com.example.ticketingappcompose.filter.custom_filter.CustomDialogViewModel
import com.example.ticketingappcompose.filter.custom_filter.PerformerAndVenueFilterType


/**
 * A simple Row Item which contain a check box and text items in a column,
 * this generic view can be used for CONNECTION, SELECT_STATUS, SELECT_DAY Bottom Sheet list items
 * @param item represents the Item in the List
 */

@Composable
fun <T> GenericListItems(
    modifier: Modifier = Modifier,
    itemIndex: Int = 0,
    currentPage: Int = 0,
    viewModel: CustomDialogViewModel2 = androidx.lifecycle.viewmodel.compose.viewModel(),
    viewModel2: CustomDialogViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    item: T,
    isItemSelected: Boolean = false,
    onItemSelected: (Boolean) -> Unit
) {

    //for days of the week
    var isSelected by remember { mutableStateOf(false) }
    //for performer venue and hours of the day
    var isPerformerItemSelected by remember { mutableStateOf(false) }




    if (item is IFilterType) {
        isSelected = viewModel.allFilterItem[itemIndex].isSelected
    }



    if (item is PerformerAndVenueFilterType) {
        isPerformerItemSelected =

            when (currentPage) {
                0 -> viewModel2.selectedFilterItem[itemIndex].isSelected
                1 -> viewModel2.recommendedFilterItem[itemIndex].isSelected
                else -> viewModel2.allFilterItem[itemIndex].isSelected
            }
    }


    Row(
        modifier = modifier.padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {


        when (item) {
            is IFilterType -> {
                DaysOfWeekCustomCheckbox(
                    viewModel = viewModel,
                    itemIndex = itemIndex,
                    item = item,
                    modifier = modifier.size(30.dp),
                    checked = isSelected,
                    onCheckedChange = {
                        isSelected = it
                        onItemSelected(it)

                    }
                )
            }

            is PerformerAndVenueFilterType -> {
                PerformerCustomCheckbox(
                    viewModel = viewModel2,
                    item = item,
                    currentPage = currentPage,
                    itemIndex = itemIndex,
                    isRecommended = item.isRecommended,
                    modifier = modifier.size(30.dp),
                    checked = isPerformerItemSelected,
                    onCheckedChange = {
                        isPerformerItemSelected = it
                        onItemSelected(it)

                    }
                )
            }
        }

        Spacer(modifier = modifier.width(16.dp))

        //Text in a Column
        Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {


            if (item is IFilterType) {
                Text(
                    textAlign = TextAlign.Center,
                    text = item.filterTitleName,
                )
                if (item.connectionLink != null)
                    Text(text = item.connectionLink ?: "",  style = TextStyle(color = Color(0xFF4E4E4E)))
                if (item.connectionEmailAddress != null)
                    Text(text = item.connectionEmailAddress ?: "",  style = TextStyle(color = Color(0xFF4E4E4E)))
            }


            else if (item is PerformerAndVenueFilterType) {
                Text(
                    modifier = modifier.padding(top = 4.dp),
                    textAlign = TextAlign.Center,
                    text = item.filterTypeName,
                    style = TextStyle(fontSize = 16.sp)
                )
            }

        }
    }

    //Straight Line
    Box(
        modifier = Modifier
            .height(.5.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(width = .5.dp, color = Color.LightGray)
    ) {

    }

}