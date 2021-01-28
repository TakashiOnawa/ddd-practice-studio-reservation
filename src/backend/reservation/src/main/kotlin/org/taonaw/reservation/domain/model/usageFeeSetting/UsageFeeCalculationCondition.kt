package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.ReservationDetails
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.shared.StudioId

data class UsageFeeCalculationCondition(
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {

    companion object {
        fun from(details: ReservationDetails): UsageFeeCalculationCondition {
            return UsageFeeCalculationCondition(
                    details.studioId, details.usageTime, details.userCount, details.practiceType, details.rentalEquipments)
        }
    }

    fun splitByMinUsageTimeUnit(): List<UsageFeeCalculationCondition> {
        return usageTime.splitMinUnit().map { copy(usageTime = it) }
    }
}