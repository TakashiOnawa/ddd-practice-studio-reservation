package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.ReservationDetails
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.shared.BasicTimeRange
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.shared.Specification

typealias UsageFeeSpecification = Specification<UsageFeeCondition>

data class UsageFeeCondition(
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {

    companion object {
        fun from(details: ReservationDetails): UsageFeeCondition {
            return UsageFeeCondition(
                    details.studioId, details.usageTime, details.userCount, details.practiceType, details.rentalEquipments)
        }
    }

    fun splitByMinUsageTimeUnit(): List<UsageFeeCondition> {
        return usageTime.splitMinUnit().map { copy(usageTime = it) }
    }
}

data class StudioSpecification(private val studioId: StudioId) : UsageFeeSpecification {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.studioId == studioId
    }
}

data class DayTypeSpecification(private val dayType: DayType, private val publicHolidays: PublicHolidays) : UsageFeeSpecification {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return dayType.contains(condition.usageTime.start.toLocalDate(), publicHolidays)
    }
}

data class TimeRangeSpecification(private val timeRange: BasicTimeRange) : UsageFeeSpecification {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        require(condition.usageTime.isMinUnit()) { "利用時間は最小単位で分割されていなければなりません。" }
        return condition.usageTime.isIn(timeRange)
    }
}

data class UserCountSpecification(private val userCountMin: Int, private val userCountMax: Int) : UsageFeeSpecification {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.userCount.isBetween(userCountMin, userCountMax)
    }
}