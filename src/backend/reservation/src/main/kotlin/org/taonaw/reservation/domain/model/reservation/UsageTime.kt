package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.AcceptingReservationStartDate
import org.taonaw.reservation.domain.model.reservationPolicy.OpeningHour
import org.taonaw.reservation.domain.model.reservationPolicy.StartTime
import org.taonaw.reservation.domain.model.shared.DateTimeRange
import java.time.LocalDateTime

data class UsageTime(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {

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
}