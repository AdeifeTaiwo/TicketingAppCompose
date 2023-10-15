package com.example.ticketingappcompose.filter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.ticketingappcompose.R
import com.example.ticketingappcompose.filter.custom_filter_2.CustomDialogViewModel2
import com.example.ticketingappcompose.filter.custom_filter_2.IFilterType
import com.example.ticketingappcompose.filter.custom_filter.PerformerAndVenueFilterType
import com.example.ticketingappcompose.filter.custom_filter.CustomDialogViewModel



@Composable
fun DaysOfWeekCustomCheckbox(
    itemIndex: Int,
    viewModel: CustomDialogViewModel2,
    item: IFilterType,
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    IconButton(modifier = modifier, onClick = {

    }) {

        //the box image frame unchecked
        Image(
            modifier = modifier.clickable {
                viewModel.toggleSpecificItem(itemIndex)
                onCheckedChange(viewModel.allFilterItem[itemIndex].isSelected)
            },

            painter = painterResource(id = R.drawable.ic_feather_check_circle_unselected),
            contentDescription = "Unchecked",
            contentScale = ContentScale.Fit
        )

        AnimatedVisibility(
            modifier = modifier,
            visible = checked,
            exit = shrinkOut(shrinkTowards = Alignment.TopStart) + fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_feather_check_circle_selected),
                contentDescription = "Checked",
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Composable
fun PerformerCustomCheckbox(
    viewModel: CustomDialogViewModel,
    itemIndex: Int,
    currentPage: Int,
    isRecommended: Boolean,
    item: PerformerAndVenueFilterType,
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    IconButton(modifier = modifier, onClick = {

    }) {

        Image(
            modifier = modifier.clickable {
                viewModel.toggleSpecificItem(index = itemIndex, item.isRecommended, currentPage)

                when (currentPage) {
                    0 -> onCheckedChange(viewModel.selectedFilterItem[itemIndex].isSelected)
                    1 -> onCheckedChange(viewModel.recommendedFilterItem[itemIndex].isSelected)
                    else -> onCheckedChange(viewModel.allFilterItem[itemIndex].isSelected)
                }
            },
            painter = painterResource(id = R.drawable.ic_feather_check_circle_unselected),
            contentDescription = "Unchecked",
            contentScale = ContentScale.Fit
        )
        AnimatedVisibility(
            modifier = modifier,
            visible = checked,
            exit = shrinkOut(shrinkTowards = Alignment.TopStart) + fadeOut()
        ) {
            //the check only (without the surrounding box)
            Image(
                painter = painterResource(id = R.drawable.ic_feather_check_circle_selected),
                contentDescription = "Checked",
                contentScale = ContentScale.Fit
            )
        }
    }
}