package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.shared.MemberId
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeCondition
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import java.time.LocalDateTime

class Reservation private constructor(
        val reservationId: ReservationId,
        private var memberId: MemberId,
        private var studioId: StudioId,
        private var usageTime: UsageTime,
        private var userCount: UserCount,
        private var practiceType: PracticeType,
        private var rentalEquipments: RentalEquipments,
        private var usageFee: UsageFee) {

    companion object {
        fun create(
                memberId: MemberId,
                studioId: StudioId,
                usageTime: UsageTime,
                userCount: UserCount,
                practiceType: PracticeType,
                rentalEquipments: RentalEquipments,
                reservationPolicy: ReservationPolicy,
                usageFeeSetting: UsageFeeSetting,
                equipments: Equipments,
                reservedAt: LocalDateTime) : Reservation {

            reservationPolicy.validateOpeningHour(usageTime)
            reservationPolicy.validateStartTime(usageTime)
            reservationPolicy.validateAcceptingReservationStartDate(usageTime, reservedAt)
            reservationPolicy.validateMaxUserCount(userCount)
            reservationPolicy.validateMaxRentalEquipmentQuantity(rentalEquipments)

            val usageFee = usageFeeSetting.calculateUsageFee(
                    UsageFeeCondition(studioId, usageTime, userCount, practiceType, rentalEquipments),
                    equipments)

            return Reservation(
                    ReservationId.newId(),
                    memberId,
                    studioId,
                    usageTime,
                    userCount,
                    practiceType,
                    rentalEquipments,
                    usageFee)
        }
    }

    fun change(
            memberId: MemberId,
            studioId: StudioId,
            usageTime: UsageTime,
            userCount: UserCount,
            practiceType: PracticeType,
            rentalEquipments: RentalEquipments,
            reservationPolicy: ReservationPolicy,
            cancellationFeeSetting: CancellationFeeSetting,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime) {

        val chargedCancellationFee = cancellationFeeSetting.chargedCancellationFee(usageTime, changedAt)
        if (this.studioId != studioId && chargedCancellationFee) {
            throw Exception()
        }
        if (this.usageTime != usageTime && chargedCancellationFee) {
            throw Exception()
        }
        if (this.usageTime != usageTime && chargedCancellationFee) {
            throw Exception()
        }

        reservationPolicy.validateOpeningHour(usageTime)
        reservationPolicy.validateStartTime(usageTime)
        reservationPolicy.validateAcceptingReservationStartDate(usageTime, changedAt)
        reservationPolicy.validateMaxUserCount(userCount)
        reservationPolicy.validateMaxRentalEquipmentQuantity(rentalEquipments)
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