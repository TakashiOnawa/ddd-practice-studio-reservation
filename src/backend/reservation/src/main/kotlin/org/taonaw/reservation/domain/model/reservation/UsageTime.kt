package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.AcceptingReservationStartDate
import org.taonaw.reservation.domain.model.reservationPolicy.OpeningHour
import org.taonaw.reservation.domain.model.reservationPolicy.StartTime
import java.time.LocalDateTime

data class UsageTime(
        val start: LocalDateTime,
        val end: LocalDateTime) {

    fun satisfy(openingHour: OpeningHour): Boolean {
        // TODO: 実装する
        return true
    }

    fun satisfy(startTime: StartTime): Boolean {
        // TODO: 実装する
        return true
    }

    fun satisfy(acceptingReservationStartDate: AcceptingReservationStartDate, currentDateTime: LocalDateTime): Boolean {
        // TODO: 実装する
        return true
    }

    fun isOverlapping(other: UsageTime): Boolean {
        return start.isBefore(other.end) && other.start.isBefore(end)
    }
}