package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.usageFee.FeeType
import org.taonaw.reservation.domain.model.shared.*
import org.taonaw.reservation.domain.shared.Specification
import java.time.LocalTime

data class UsageFeeSpecification(
        val practiceTypeTypeSpecification: PracticeTypeTypeSpecification?,
        val studioSpecification: StudioSpecification?,
        val dayTypeSpecification: DayTypeSpecification?,
        val userCountSpecification: UserCountSpecification?,
        val timeRangeSpecification: TimeRangeSpecification,
        val usageTimeSpecification: UsageTimeSpecification,
        val fee: Fee,
        val feeType: FeeType) {
}

data class PracticeTypeTypeSpecification(val practiceType: PracticeType) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.practiceType == practiceType
    }
}

data class StudioSpecification(val studioId: StudioId) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.studioId == studioId
    }
}

data class DayTypeSpecification(val dayType: DayType, private val publicHolidays: PublicHolidays) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return dayType.contains(condition.usageTime.start.toLocalDate(), publicHolidays)
    }
}

data class UserCountSpecification(val userCountMin: Int, private val userCountMax: Int) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.userCount.isBetween(userCountMin, userCountMax)
    }
}

data class TimeRangeSpecification(val timeRange: TimeRange) : Specification<UsageFeeCondition> {
    companion object {
        fun default(): TimeRangeSpecification {
            return TimeRangeSpecification(BasicTimeRange(LocalTime.MIN, LocalTime.MIN))
        }
    }

    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.usageTime.contains(timeRange)
    }
}

data class UsageTimeSpecification(val minutes: Long, val type: Type) {
    enum class Type {
        GREATER_THAN, FIXED
    }

    companion object {
        fun default(): UsageTimeSpecification {
            return UsageTimeSpecification(UsageTime.MIN_MINUTES_UNIT, Type.GREATER_THAN)
        }
    }
}