package org.taonaw.reservation.domain.model.reservationPolicy

import java.time.LocalTime

data class OpeningHour(
        val start: LocalTime,
        val end: LocalTime){

    fun isAllDay() : Boolean {
        return start == end
    }
}