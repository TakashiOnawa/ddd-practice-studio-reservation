package org.taonaw.reservation.usecase.reservation.reserveStudio

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.reservation.RentalEquipments
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.model.shared.MemberId
import org.taonaw.reservation.domain.model.shared.StudioId

data class ReserveStudioCommand(
        val memberId: MemberId,
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {
}