package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.shared.PracticeType
import org.taonaw.reservation.domain.model.shared.StudioId

/**
 * 予約内容
 */
data class ReservationDetails(
        val user: User,
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {
}