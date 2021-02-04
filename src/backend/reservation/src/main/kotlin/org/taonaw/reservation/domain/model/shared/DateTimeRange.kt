package org.taonaw.reservation.domain.model.shared

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
        return other.start <= start && end <= other.end
    }

    fun isIn(timeRange: TimeRange): Boolean {
        if (timeRange.isAllDay()) return true
        if (isIn(timeRange.toDateTimeRange(start.toLocalDate()))) return true
        return false
    }

    fun isOverlapping(other: DateTimeRange): Boolean {
        if (start == other.start && end == other.end)
            return true
        return start < other.end && other.start < end
    }

    fun overlappingCount(others: List<DateTimeRange>): Int {
        return others.count { it.isOverlapping(this) }
    }

    fun isPassed(currentDateTime: LocalDateTime): Boolean {
        return start <= currentDateTime
    }

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
    override fun toString(): String
}

data class BasicDateTimeRange(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {
}