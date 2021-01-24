package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.shared.TimeRange
import java.time.LocalTime

/**
 * 営業時間
 */
data class OpeningHour(
        override val start: LocalTime,
        override val end: LocalTime) : TimeRange {

    fun isSatisfiedBy(usageTime: UsageTime): Boolean {
        return if (isAllDay()) {
            true
        } else {
            usageTime.isIn(this.toDateTimeRange(usageTime.start.toLocalDate()))
        }
    }

    private fun isAllDay() : Boolean {
        return start == end
    }
}