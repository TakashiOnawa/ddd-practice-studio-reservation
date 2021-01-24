package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.DateTimeRange
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class UsageTime(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {

    companion object {
        private const val MIN_MINUTES_UNIT: Int = 60
    }

    init {
        require(!(start.second != 0 || end.nano != 0)) { "開始日時に秒の指定はできません。" }
        require(!(end.second != 0 || end.nano != 0)) { "終了日時に秒の指定はできません。" }
        require(ChronoUnit.MINUTES.between(start, end) % MIN_MINUTES_UNIT == 0L) { "$MIN_MINUTES_UNIT 分単位でなければなりません。" }
    }

    fun splitMinUnit(): List<UsageTime> {
        // TODO: 実装する
        return listOf(this)
    }
}