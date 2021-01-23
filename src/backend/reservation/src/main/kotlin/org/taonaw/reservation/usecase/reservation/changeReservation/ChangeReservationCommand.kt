package org.taonaw.reservation.usecase.reservation.changeReservation

import org.taonaw.reservation.domain.model.reservation.*
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.shared.MemberId
import org.taonaw.reservation.domain.model.shared.StudioId

data class ChangeReservationCommand(
        val reservationId: ReservationId,
        val memberId: MemberId,
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {
}