package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.shared.TimeRange
import java.time.LocalTime

data class OpeningHour(
        override val start: LocalTime,
        override val end: LocalTime) : TimeRange {

    fun isAllDay() : Boolean {
        return start == end
    }
}