package org.taonaw.reservation.domain.model.shared

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface TimeRange {
    val start: LocalTime
    val end: LocalTime

    fun hasStartTimeSeconds(): Boolean {
        return start.second != 0 || start.nano != 0
    }

    fun hasEndTimeSeconds(): Boolean {
        return end.second != 0 || end.nano != 0
    }

    fun isStraddleTheDay(): Boolean {
        return !start.isBefore(end)
    }

    fun isOverlapped(other: TimeRange): Boolean {
        val sampleDate = LocalDate.now()

        val thisStartDateTime = sampleDate.atTime(start)
        val thisEndDateTime = if (isStraddleTheDay()) {
            sampleDate.plusDays(1).atTime(end)
        } else {
            sampleDate.atTime(end)
        }

        val otherStartDateTime = sampleDate.atTime(other.start)
        val otherEndDateTime = if (other.isStraddleTheDay()) {
            sampleDate.plusDays(1).atTime(other.end)
        } else {
            sampleDate.atTime(other.end)
        }

        return thisStartDateTime.isBefore(otherEndDateTime) && otherStartDateTime.isBefore(thisEndDateTime)
    }

    fun toDateTimeRange(date: LocalDate): DateTimeRange {
        val startDateTime: LocalDateTime = date.atTime(start)
        val endDateTime: LocalDateTime = if (isStraddleTheDay()) {
            date.plusDays(1).atTime(end)
        } else {
            date.atTime(end)
        }

        return BasicDateTimeRange(startDateTime, endDateTime)
    }

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
    override fun toString(): String
}

data class BasicTimeRange(
        override val start: LocalTime,
        override val end: LocalTime) : TimeRange {
}