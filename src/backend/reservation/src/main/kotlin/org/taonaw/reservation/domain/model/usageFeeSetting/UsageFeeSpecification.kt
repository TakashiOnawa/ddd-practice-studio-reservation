package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.shared.BasicTimeRange
import org.taonaw.reservation.domain.model.shared.Fee
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.shared.Specification

data class UsageFeeSpecification(
        val specifications: List<Specification<UsageFeeCalculationCondition>>,
        val fee: Fee) : Specification<UsageFeeCalculationCondition> {

    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return specifications.all { it.isSatisfiedBy(condition) }
    }
}

data class PracticeTypeSpecification(private val practiceType: PracticeType) : Specification<UsageFeeCalculationCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return condition.practiceType == practiceType
    }
}

data class StudioSpecification(private val studioId: StudioId) : Specification<UsageFeeCalculationCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return condition.studioId == studioId
    }
}

data class DayTypeSpecification(private val dayType: DayType, private val publicHolidays: PublicHolidays) : Specification<UsageFeeCalculationCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return dayType.contains(condition.usageTime.start.toLocalDate(), publicHolidays)
    }
}

data class TimeRangeSpecification(private val timeRange: BasicTimeRange) : Specification<UsageFeeCalculationCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return condition.usageTime.isIn(timeRange)
    }
}

data class UserCountSpecification(private val userCountMin: Int, private val userCountMax: Int) : Specification<UsageFeeCalculationCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCalculationCondition): Boolean {
        return condition.userCount.isBetween(userCountMin, userCountMax)
    }
}