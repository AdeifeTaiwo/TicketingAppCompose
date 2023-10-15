package com.example.ticketingappcompose.filter.custom_filter


/**
 * this data class provides temporary data
 * for performer, venue and hours of the day, viewModel class
 */
object PerformerDataProvider {

    private val allPerformer: List<Performer> = listOf(
        Performer(filterTypeName = "Los Angeles Lakers"),
        Performer(filterTypeName = "Los Angeles Clippers"),
        Performer(filterTypeName = "Los Angeles Dodgers"),
        Performer(filterTypeName = "Los Angeles rams")
    )
    private val allRecommendedPerformer: List<Performer> = listOf(
        Performer(filterTypeName = "Los Angeles Lakers", isRecommended = true),
        Performer(filterTypeName = "Los Angeles Clippers", isRecommended = true),
    )

    fun fetchAllRecommendedPerformer(): List<Performer> = allRecommendedPerformer
    fun fetchAllPerformers(): List<Performer> = allPerformer
}


object VenueDataProvider {

    private val allVenue: List<Venue> = listOf(
        Venue(filterTypeName = "Crypto.com Arena"),
        Venue(filterTypeName = "Dodgers Stadium"),
        Venue(filterTypeName = "Angel Stadium of Anaheim"),
        Venue(filterTypeName = "SoFI Stadium")
    )

    private val allRecommendedVenue: List<Venue> = listOf(
        Venue(filterTypeName = "Crypto.com Arena", isRecommended = true),
        Venue(filterTypeName = "Dodgers Stadium", isRecommended = true),

        )

    fun fetchAllRecommendedVenue(): List<Venue> = allRecommendedVenue
    fun fetchAllVenues(): List<Venue> = allVenue

}

//Hours of Day

object HoursOfDaysDataProvider {

    private val allHoursOfDayFilterType: List<HoursOfDay> = listOf(

        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWELVE_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.ONE_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWO_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.THREE_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.FOUR_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.FIVE_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.SIX_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.SEVEN_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.EIGHT_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.NINE_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TEN_AM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.ELEVEN_AM.value
        ),

        //PM
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWELVE_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.ONE_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWO_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.THREE_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.FOUR_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.FIVE_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.SIX_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.SEVEN_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.EIGHT_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.NINE_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TEN_PM.value
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.ELEVEN_PM.value
        )
    )

    private val allRecommendedHoursOfDays: List<HoursOfDay> = listOf(

        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWELVE_AM.value,
            isRecommended = true
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.ONE_AM.value,
            isRecommended = true
        ),
        HoursOfDay(
            filterTypeName = HoursOfDayFilterType.TWO_AM.value,
            isRecommended = true
        ),
    )

    fun fetchAllRecommendedHoursOfDays(): List<HoursOfDay> = allRecommendedHoursOfDays

    fun fetchAllHoursOfDayFilterType(): List<HoursOfDay> = allHoursOfDayFilterType
}


data class Venue(
    override val filterTypeName: String ="",
    override var isSelected: Boolean = false,
    override var isRecommended: Boolean = false
) : PerformerAndVenueFilterType {

    override fun toggleSelectedState() {
        isSelected = !isSelected
    }
}


data class Performer(
    override val filterTypeName: String ="",
    override var isSelected: Boolean = false,
    override var isRecommended: Boolean = false
) : PerformerAndVenueFilterType {

    override fun toggleSelectedState() {
        isSelected = !isSelected
    }
}


data class HoursOfDay(
    override var isSelected: Boolean = false,
    override val filterTypeName: String ="",
    override var isRecommended: Boolean = false

) : PerformerAndVenueFilterType {

    override fun toggleSelectedState() {
        isSelected = !isSelected
    }

}


/**
 * the inherited interface
 */
interface PerformerAndVenueFilterType {
    val filterTypeName: String
    var isSelected: Boolean
    var isRecommended: Boolean

    fun toggleSelectedState() {

    }
}

//represent time enum
enum class HoursOfDayFilterType(val value: String) {
    TWELVE_AM("12AM"),
    ONE_AM("1AM"),
    TWO_AM("2AM"),
    THREE_AM("3AM"),
    FOUR_AM("4AM"),
    FIVE_AM("5AM"),
    SIX_AM("6AM"),
    SEVEN_AM("7AM"),
    EIGHT_AM("8AM"),
    NINE_AM("9AM"),
    TEN_AM("10AM"),
    ELEVEN_AM("11AM"),
    TWELVE_PM("12PM"),
    ONE_PM("1PM"),
    TWO_PM("2PM"),
    THREE_PM("3PM"),
    FOUR_PM("4PM"),
    FIVE_PM("5PM"),
    SIX_PM("6PM"),
    SEVEN_PM("7PM"),
    EIGHT_PM("8PM"),
    NINE_PM("9PM"),
    TEN_PM("10PM"),
    ELEVEN_PM("11PM"),
}
