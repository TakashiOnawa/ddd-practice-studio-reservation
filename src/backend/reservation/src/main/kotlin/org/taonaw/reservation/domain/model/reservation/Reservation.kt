package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.shared.MemberId
import org.taonaw.reservation.domain.model.shared.StudioId
import java.time.LocalDateTime

class Reservation private constructor(
        val reservationId: ReservationId,
        private var memberId: MemberId,
        private var studioId: StudioId,
        private var usageTime: UsageTime,
        private var userCount: UserCount,
        private var practiceType: PracticeType,
        private var rentalEquipments: RentalEquipments) {

    companion object {
        fun create(
                memberId: MemberId,
                studioId: StudioId,
                usageTime: UsageTime,
                userCount: UserCount,
                practiceType: PracticeType,
                rentalEquipments: RentalEquipments,
                currentDateTime: LocalDateTime,
                reservationPolicy: ReservationPolicy) : Reservation {

            reservationPolicy.validateOpeningHour(usageTime)
            reservationPolicy.validateStartTime(usageTime)
            reservationPolicy.validateAcceptingReservationStartDate(usageTime, currentDateTime)
            reservationPolicy.validateMaxUserCount(userCount)
            reservationPolicy.validateMaxRentalEquipmentQuantity(rentalEquipments)

            return Reservation(
                    ReservationId.newId(),
                    memberId,
                    studioId,
                    usageTime,
                    userCount,
                    practiceType,
                    rentalEquipments)
        }
    }

    fun isDuplicated(other: Reservation): Boolean {
        return this != other &&
                studioId == other.studioId &&
                usageTime.isOverlapping(other.usageTime)
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