package com.example.ticketingappcompose.filter.custom_filter_2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CustomDialogViewModel2 : ViewModel() {

    private var selectedFilterItem: MutableList<IFilterType> = mutableListOf()
    lateinit var allFilterItem: MutableList<IFilterType>



    //handled select all button for all items tab
    var shouldSelectAllButtonPressed = mutableStateOf(false)


    /**
     * handles toggling of selected items
     */
    fun toggleAllItems() {
        shouldSelectAllButtonPressed.value = !shouldSelectAllButtonPressed.value
        toggleAllButtonSetup()
    }


    private fun toggleAllButtonSetup() {

        //when you select all items update all selected item as well
        for (i in allFilterItem.indices) {
            allFilterItem[i].isSelected = shouldSelectAllButtonPressed.value

            if (shouldSelectAllButtonPressed.value)
                selectedFilterItem.add(allFilterItem[i])
            else
                selectedFilterItem.remove(allFilterItem[i])
        }


    }

    fun initFilterType(filterName: String) {
        when (filterName) {

            CONNECTION -> {
                allFilterItem =
                    ConnectionProvider.fetchAllConnections().toMutableList()

            }
           STATUS -> {
                allFilterItem =
                    StatusFilterDataProvider.fetchAllStatusType().toMutableList()
            }
            DAYS_OF_WEEK -> {
                allFilterItem =
                    DaysOfWeekDataProvider.fetchAllDaysOfWeekFilterType().toMutableList()
            }
        }

    }


    fun toggleSpecificItem(index: Int) {
        allFilterItem[index].toggleSelectedState()
    }


    fun removeItem(item: IFilterType) {
        selectedFilterItem.remove(item)
    }

    fun addItem(item: IFilterType) {
        selectedFilterItem.add(item)
    }


    companion object {
        const val CONNECTION = "Connection"
        const val STATUS = "Status"
        const val DAYS_OF_WEEK = "Days Of Week"
    }

}