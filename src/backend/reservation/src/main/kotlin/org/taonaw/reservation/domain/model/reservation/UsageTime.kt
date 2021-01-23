package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.AcceptingReservationStartDate
import org.taonaw.reservation.domain.model.reservationPolicy.OpeningHour
import org.taonaw.reservation.domain.model.reservationPolicy.StartTime
import org.taonaw.reservation.domain.model.shared.DateTimeRange
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class UsageTime(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {

    private val minUnit: Int = 60

    init {
        require(!(start.second != 0 || end.nano != 0)) { "開始日時に秒の指定はできません。" }
        require(!(end.second != 0 || end.nano != 0)) { "終了日時に秒の指定はできません。" }
        require(ChronoUnit.MINUTES.between(start, end) % minUnit == 0L) { "1 時間単位でなければなりません。" }
    }

    fun splitMinUnit(): List<UsageTime> {
        // TODO: 実装する
        return listOf(this)
    }

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