package com.example.ticketingappcompose.filter

object FiltersTypeDataProvider {

    private val filterList: List<Filter> = listOf(
        Filter(FilterType.START_DATE, "Today"),
        Filter(FilterType.END_DATE, "None"),
        Filter(FilterType.CONNECTION, "Any"),
        Filter(FilterType.STATUS, "Any"),
        Filter(FilterType.DAYS_OF_WEEK, "Any"),
        Filter(FilterType.HOUR_OF_DAY, "Any"),
        Filter(FilterType.PERFORMER, "Any"),
        Filter(FilterType.VENUE, "Any"),
    )

    fun getFilterList(): List<Filter> = filterList


}

data class Filter(
    val filterType: FilterType,
    val filterInitialSelection: String
)

enum class FilterType(val value: String) {
    START_DATE("Start Date"),
    END_DATE("End Date"),
    CONNECTION("Connection"),
    STATUS("Status"),
    DAYS_OF_WEEK("Day of Week"),
    HOUR_OF_DAY("Hour of Day"),
    PERFORMER("Performer"),
    VENUE("Venue")
}