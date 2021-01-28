package org.taonaw.reservation.domain.model.shared

import org.taonaw.reservation.domain.model.reservation.UsageTime
import java.time.LocalDateTime

interface DateTimeRange {
    val start: LocalDateTime
    val end: LocalDateTime

    fun hasStartSeconds(): Boolean {
        return start.second != 0 || start.nano != 0
    }

    fun hasEndSeconds(): Boolean {
        return end.second != 0 || end.nano != 0
    }

    fun isIn(other: DateTimeRange): Boolean {
        return !start.isBefore(other.start) && !end.isAfter(other.end)
    }

    fun isIn(timeRange: TimeRange): Boolean {
        if (timeRange.isAllDay()) return true
        if (isIn(timeRange.toDateTimeRange(start.toLocalDate()))) return true
        return false
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