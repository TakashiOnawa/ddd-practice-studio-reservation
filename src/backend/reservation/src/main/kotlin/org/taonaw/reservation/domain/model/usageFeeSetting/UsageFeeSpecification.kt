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

data class StudioSpecification(private val studioId: StudioId) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        TODO("Not yet implemented")
    }
}

data class TimeRangeSpecification(private val timeRange: BasicTimeRange) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        TODO("Not yet implemented")
    }
}

data class UserCountSpecification(private val userCountLimit: Int) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        TODO("Not yet implemented")
    }
}