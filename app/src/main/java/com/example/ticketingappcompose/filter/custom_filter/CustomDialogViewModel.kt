package com.example.ticketingappcompose.filter.custom_filter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class CustomDialogViewModel : ViewModel() {

    var selectedFilterItem : MutableList<PerformerAndVenueFilterType> = mutableListOf()
    lateinit var allFilterItem: MutableList<PerformerAndVenueFilterType>
    lateinit var recommendedFilterItem: MutableList<PerformerAndVenueFilterType>

    //handled select all button for recommended tab items
    var shouldSelectAllRecommendedButtonPressed = mutableStateOf(false)

    //handled select all button for all items tab
    var shouldSelectAllButtonPressed = mutableStateOf(false)


    /**
     * handles toggling of selected items
     */
    fun toggleAllItems(currentPage: Int) {
        if (currentPage == 1) {
            shouldSelectAllRecommendedButtonPressed.value =
                !shouldSelectAllRecommendedButtonPressed.value
        } else if (currentPage == 2) {
            shouldSelectAllButtonPressed.value = !shouldSelectAllButtonPressed.value
        }
        toggleButtonSetup(currentPage)
    }


    private fun toggleButtonSetup(currentPage: Int) {

        //when you select all recommened items update all selected item as well
        when(currentPage) {
            1 -> {
                for (i in recommendedFilterItem.indices) {
                    recommendedFilterItem[i].isSelected = shouldSelectAllRecommendedButtonPressed.value

                    if (shouldSelectAllRecommendedButtonPressed.value)
                        selectedFilterItem.add(recommendedFilterItem[i])
                    else
                        selectedFilterItem.remove(recommendedFilterItem[i])
                }
            }

            //when you select all items update all selected item as well
            2 -> {
                for (i in allFilterItem.indices) {
                    allFilterItem[i].isSelected = shouldSelectAllButtonPressed.value

                    if (shouldSelectAllButtonPressed.value)
                        selectedFilterItem.add(allFilterItem[i])
                    else
                        selectedFilterItem.remove(allFilterItem[i])
                }
            }
        }
    }

    fun initFilterType(filterName: String) {
        when (filterName) {

            PERFORMER -> {
                allFilterItem =
                    PerformerDataProvider.fetchAllPerformers().toMutableList()
                recommendedFilterItem =
                    PerformerDataProvider.fetchAllRecommendedPerformer().toMutableList()
            }

            VENUE -> {
                allFilterItem =
                    VenueDataProvider.fetchAllVenues().toMutableList()
                recommendedFilterItem =
                    VenueDataProvider.fetchAllRecommendedVenue().toMutableList()
            }

            HOURS_OF_DAY -> {
                allFilterItem =
                    HoursOfDaysDataProvider.fetchAllHoursOfDayFilterType().toMutableList()
                recommendedFilterItem =
                    HoursOfDaysDataProvider.fetchAllRecommendedHoursOfDays().toMutableList()
            }
        }

    }


    fun toggleSpecificItem(index: Int, isRecommended: Boolean, currentPage: Int) {
        val item = allFilterItem[index]
        val isSelected = item.isSelected

        if (currentPage == 0 && isRecommended) {
            selectedFilterItem[index].toggleSelectedState()
            recommendedFilterItem[index].toggleSelectedState()
        } else if (currentPage == 0 && isRecommended == false) {
            selectedFilterItem[index].toggleSelectedState()
            allFilterItem[index].toggleSelectedState()
        } else if (currentPage == 1) {
            recommendedFilterItem[index].toggleSelectedState()
        } else allFilterItem[index].toggleSelectedState()


    }



    fun removeItem(item: PerformerAndVenueFilterType) {
        selectedFilterItem.remove(item)
    }

    fun addItem(item: PerformerAndVenueFilterType) {
        selectedFilterItem.add(item)
    }


    companion object {
        const val PERFORMER = "Performer"
        const val VENUE = "Venue"
        const val HOURS_OF_DAY = "Hours Of Day"
    }
}