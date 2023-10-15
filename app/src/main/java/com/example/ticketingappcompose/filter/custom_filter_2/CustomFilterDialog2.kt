package com.example.ticketingappcompose.filter.custom_filter_2

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticketingappcompose.filter.components.FilterHeaderComponent
import com.example.ticketingappcompose.filter.components.FilterHeaderWithBackArrow
import com.example.ticketingappcompose.filter.components.FilterSubHeaderComponent
import com.example.ticketingappcompose.filter.components.GenericListItems
import com.example.ticketingappcompose.filter.components.SaveFilterButtonComponent
import com.example.ticketingappcompose.filter.components.SelectAllButtonComponent
import com.example.ticketingappcompose.filter.custom_filter_2.CustomDialogViewModel2.Companion.DAYS_OF_WEEK
import com.example.ticketingappcompose.filter.custom_filter_2.CustomDialogViewModel2.Companion.CONNECTION

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFilterDialog2(
    viewModel: CustomDialogViewModel2 = androidx.lifecycle.viewmodel.compose.viewModel(),
    filterType: String = DAYS_OF_WEEK,
    onDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onPrimaryButtonClick: () -> Unit
) {
    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetContent = {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp)
            ) {

                viewModel.initFilterType(filterType)

                val allFilterItem = viewModel.allFilterItem
                val hasSelectedAll: Boolean = viewModel.shouldSelectAllButtonPressed.value


                if (filterType == CONNECTION) {
                    FilterHeaderComponent(headerText = filterType) { onDismiss() }
                } else {
                    FilterHeaderWithBackArrow(headerText = filterType) {

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                if (filterType == DAYS_OF_WEEK) {
                    SelectAllButtonComponent(hasSelectedAll = hasSelectedAll) {
                        viewModel.toggleAllItems()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                FilterSubHeaderComponent(text = "Select $filterType")

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.height(300.dp)) {


                    LazyColumn(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .wrapContentSize(),
                        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 4.dp)

                    ) {

                        items(allFilterItem.size) { index ->

                            GenericListItems(
                                viewModel = viewModel,
                                item = viewModel.allFilterItem[index],
                                itemIndex = index,
                                onItemSelected = { isSelected ->
                                    try {
                                        if (isSelected) {
                                            viewModel.addItem(allFilterItem[index])
                                        } else viewModel.removeItem(allFilterItem[index])

                                    } catch (e: Exception) {
                                        Log.d("exception", e.printStackTrace().toString())
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                SaveFilterButtonComponent(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onPrimaryButtonClick = { onPrimaryButtonClick() })


                Spacer(modifier = Modifier.height(60.dp))
            }
        }) {

    }

}


@Preview
@Composable
fun ConnectionDialogPreview() {
    CustomFilterDialog2(
        onDismiss = { },
        onResetClick = { }) {

    }
}