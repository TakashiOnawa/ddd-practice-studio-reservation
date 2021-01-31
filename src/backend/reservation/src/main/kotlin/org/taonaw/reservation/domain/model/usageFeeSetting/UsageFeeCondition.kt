package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.shared.DateTimeRange
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.model.shared.TimeRange

data class UsageFeeCondition(
        private val practiceTypeCondition: PracticeType?,
        private val studioCondition: StudioId?,
        private val dayTypeCondition: DayTypeCondition?,
        private val userCountCondition: UserCountCondition?,
        private val timeRangeCondition: TimeRange?,
        private val usageTimeCondition: UsageTimeCondition?) {

    fun getMatchDateTimeRanges(calculationCondition: UsageFeeCalculationCondition): List<DateTimeRange> {
        val dateTimeRanges = mutableListOf<UsageTime>()
        if (practiceTypeCondition != null && practiceTypeCondition != calculationCondition.practiceType)
            return dateTimeRanges
        if (studioCondition != null && studioCondition != calculationCondition.studioId)
            return dateTimeRanges
        if (dayTypeCondition != null && !dayTypeCondition.isSatisfiedBy(calculationCondition))
            return dateTimeRanges
        if (userCountCondition != null && !userCountCondition.isSatisfiedBy(calculationCondition))
            return dateTimeRanges

        if (timeRangeCondition != null) {
            dateTimeRanges.addAll(calculationCondition.usageTime.extract(timeRangeCondition))
        } else {
            dateTimeRanges.add(calculationCondition.usageTime)
        }

        if (usageTimeCondition != null) {
            if (usageTimeCondition.type == UsageTimeCondition.Type.FIX) {
                for (dateTimeRange in dateTimeRanges.toList()) {
                    if (dateTimeRange.durationAsMinutes() < usageTimeCondition.minutes) {
                        dateTimeRanges.remove(dateTimeRange)
                    } else {

                    }
                }
            } else {

            }
        }

        // 固定時間料金設定
        //  時間帯、固定時間

        // 以上時間料金設定
        //  時間帯、以上時間

        return dateTimeRanges
    }
}

data class DayTypeCondition(private val dayType: DayType, private val publicHolidays: PublicHolidays) {
    fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return dayType.contains(condition.usageTime.start.toLocalDate(), publicHolidays)
    }
}

data class UserCountCondition(private val userCountMin: Int, private val userCountMax: Int) {
    fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return condition.userCount.isBetween(userCountMin, userCountMax)
    }
}

data class UsageTimeCondition(val minutes: Long, val type: Type) {
    enum class Type {
        FIX, OVER
    }
}