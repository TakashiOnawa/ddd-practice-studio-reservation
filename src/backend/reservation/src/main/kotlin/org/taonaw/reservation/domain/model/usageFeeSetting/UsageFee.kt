package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.shared.DateTimeRange
import org.taonaw.reservation.domain.model.shared.Fee

data class UsageFee(
        private val usageFeeCondition: UsageFeeCondition,
        private val fee: Fee,
        private val type: Type) {

    enum class Type(private val value: Int) {
        HOURLY(1),
        FIXED(2);
    }

    fun getDateTimeRangeFee(calculationCondition: UsageFeeCalculationCondition): DateTimeRangeFee {
        return DateTimeRangeFee(usageFeeCondition.getMatchDateTimeRanges(calculationCondition), fee, type)
    }
}

data class DateTimeRangeFee(
        val dateTimeRanges: List<DateTimeRange>,
        val fee: Fee,
        val usageFeeType: UsageFee.Type) {

}