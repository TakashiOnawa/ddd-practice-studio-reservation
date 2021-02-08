package org.taonaw.facility.domain.model.practiceType

data class AcceptingReservationStartDate(
        private val dateValue: Int,
        private val dateType: DateType) {

    enum class DateType(private val value: Int) {
        DAYS_AGO(1),
        WEEKS_AGO(2),
        MONTHS_AGO(3);

        fun create(dateValue: Int): AcceptingReservationStartDate {
            return AcceptingReservationStartDate(dateValue, this)
        }
    }

    companion object {
        const val DATE_VALUE_MIN = 0
    }

    init {
        require(dateValue >= DATE_VALUE_MIN) {
            "日付は $DATE_VALUE_MIN 以上でなければなりません。"
        }
    }
}