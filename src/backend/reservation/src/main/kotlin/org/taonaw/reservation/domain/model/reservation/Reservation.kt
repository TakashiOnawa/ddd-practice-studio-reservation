package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.shared.MemberId
import org.taonaw.reservation.domain.model.shared.StudioId
import java.time.LocalDateTime

class Reservation private constructor(
        private val reservationId: ReservationId,
        private var memberId: MemberId,
        private var studioId: StudioId,
        private var usageDateTime: UsageDateTime,
        private var userCount: UserCount,
        private var practiceType: PracticeType,
        private var rentalEquipments: RentalEquipments) {

    companion object {
        fun create(
            memberId: MemberId,
            studioId: StudioId,
            usageDateTime: UsageDateTime,
            userCount: UserCount,
            practiceType: PracticeType,
            rentalEquipments: RentalEquipments,
            currentDateTime: LocalDateTime,
            reservationPolicy: ReservationPolicy) : Reservation {

            reservationPolicy.validateReservableDateTime(usageDateTime)
            reservationPolicy.validateStartTime(usageDateTime)
            reservationPolicy.validateReservationStartDateTime(usageDateTime, currentDateTime)
            reservationPolicy.validateUserMaxCount(userCount)
            reservationPolicy.validateRentalEquipmentMaxQuantity(rentalEquipments)

            return Reservation(
                    ReservationId.newId(),
                    memberId,
                    studioId,
                    usageDateTime,
                    userCount,
                    practiceType,
                    rentalEquipments)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reservation

        if (reservationId != other.reservationId) return false

        return true
    }

    override fun hashCode(): Int {
        return reservationId.hashCode()
    }
}