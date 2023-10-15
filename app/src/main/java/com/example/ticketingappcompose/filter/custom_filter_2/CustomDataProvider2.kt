package com.example.ticketingappcompose.filter.custom_filter_2

object ConnectionProvider {
    private val allConnections: List<Connection> = listOf(
        Connection(
            filterTitleName = ConnectionType.ANY.value
        ),

        Connection(
            filterTitleName = ConnectionType.TICKET_MASTER.value,
            connectionLink = "Ticketmaster.com",
            connectionEmailAddress = "gary@lolay.com"
        ),

        Connection(
            filterTitleName = ConnectionType.ANY.value,
            connectionLink = "Ticketmaster.com",
            connectionEmailAddress = "gary@lolay.com"
        )
    )

    fun fetchAllConnections(): List<Connection> = allConnections
}

object DaysOfWeekDataProvider {
    private val allDaysOFWeekFilterType: List<DaysOfWeek> = listOf(
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.MONDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.TUESDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.WEDNESDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.THURSDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.FRIDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.SATURDAY.value
        ),
        DaysOfWeek(
            filterTitleName = DaysOFWeekFilterType.SUNDAY.value
        )
    )

    fun fetchAllDaysOfWeekFilterType(): List<DaysOfWeek> = allDaysOFWeekFilterType
}



object StatusFilterDataProvider {
    private val allStatusType: List<Status> = listOf(
        Status(
            filterTitleName = StatusType.ANY.value
        ),
        Status(
            filterTitleName = StatusType.LISTED.value
        ),
        Status(
            filterTitleName = StatusType.UNLISTED.value
        ),
        Status(
            filterTitleName = StatusType.PENDING.value
        ),
        Status(
            filterTitleName = StatusType.SOLD.value
        )
    )

    fun fetchAllStatusType(): List<Status> = allStatusType
}


data class Status(
    override val filterTitleName: String,
    override var isSelected: Boolean = false,

    override val connectionLink: String? = null,
    override val connectionEmailAddress: String? = null,
) : IFilterType  {
    override fun toggleSelectedState() {
        isSelected = !isSelected
    }

}



data class DaysOfWeek(
    override val filterTitleName: String,
    override var isSelected: Boolean = false,

    override val connectionLink: String? = null,
    override val connectionEmailAddress: String? = null,
) : IFilterType  {
    override fun toggleSelectedState() {
        isSelected = !isSelected
    }

}


data class Connection(
    override val filterTitleName: String,
    override val connectionLink: String? = null,
    override val connectionEmailAddress: String? = null,
    override var isSelected: Boolean = false
) : IFilterType {
    override fun toggleSelectedState() {
        isSelected = !isSelected
    }

}


/**
 * the inherited interface
 */
interface IFilterType {
    val filterTitleName: String
    var isSelected: Boolean
    val connectionLink: String?
    val connectionEmailAddress: String?

    fun toggleSelectedState() {
    }
}




enum class ConnectionType(val value: String) {
    ANY("Any"),
    TICKET_MASTER("Ticketmaster")
}

enum class DaysOFWeekFilterType(val value: String) {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday")
}

enum class StatusType(val value: String) {
    ANY("Any"),
    UNLISTED("Unlisted"),
    LISTED("Listed"),
    PENDING("Pending"),
    SOLD("Sold")
}
