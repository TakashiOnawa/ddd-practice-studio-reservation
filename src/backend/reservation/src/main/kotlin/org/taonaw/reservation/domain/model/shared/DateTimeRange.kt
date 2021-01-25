package org.taonaw.reservation.domain.model.shared

import org.taonaw.reservation.domain.model.reservation.UsageTime
import java.time.LocalDateTime

interface DateTimeRange {
    val start: LocalDateTime
    val end: LocalDateTime

    fun isIn(other: DateTimeRange): Boolean {
        return !start.isBefore(other.start) && !end.isAfter(other.end)
    }

    fun isNotIn(other: DateTimeRange): Boolean {
        return !isIn(other)
    }

    fun isOverlapping(other: UsageTime): Boolean {
        return start.isBefore(other.end) && other.start.isBefore(end)
    }

    fun isPassed(currentDateTime: LocalDateTime): Boolean {
        return !start.isBefore(currentDateTime)
    }

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
    override fun toString(): String
}

data class BasicDateTimeRange(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {
}