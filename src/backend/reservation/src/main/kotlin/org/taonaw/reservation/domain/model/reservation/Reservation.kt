package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeCondition
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import java.time.LocalDateTime

class Reservation private constructor(
        val reservationId: ReservationId,
        val user: User,
        val details: Details,
        val usageFee: UsageFee) {

    companion object {
        fun create(
                user: User,
                studioId: StudioId,
                usageTime: UsageTime,
                userCount: UserCount,
                practiceType: PracticeType,
                rentalEquipments: RentalEquipments,
                reservationPolicy: ReservationPolicy,
                usageFeeSetting: UsageFeeSetting,
                equipments: Equipments,
                reservedAt: LocalDateTime) : Reservation {

            val details = Details(studioId, usageTime, userCount, practiceType, rentalEquipments)
            details.validate(reservationPolicy, reservedAt)

            val usageFee = usageFeeSetting.calculateUsageFee(
                    UsageFeeCondition(studioId, usageTime, userCount, practiceType, rentalEquipments),
                    equipments)

            return Reservation(ReservationId.newId(), user, details, usageFee)
        }

        data class Details(
                val studioId: StudioId,
                val usageTime: UsageTime,
                val userCount: UserCount,
                val practiceType: PracticeType,
                val rentalEquipments: RentalEquipments) {

            fun validate(reservationPolicy: ReservationPolicy, currentDateTime: LocalDateTime) {
                reservationPolicy.validateOpeningHour(usageTime)
                reservationPolicy.validateStartTime(usageTime)
                reservationPolicy.validateAcceptingReservationStartDate(usageTime, currentDateTime)
                reservationPolicy.validateMaxUserCount(userCount)
                reservationPolicy.validateMaxRentalEquipmentQuantity(rentalEquipments)
            }

            fun change(
                    studioId: StudioId,
                    usageTime: UsageTime,
                    userCount: UserCount,
                    practiceType: PracticeType,
                    rentalEquipments: RentalEquipments,
                    reservationPolicy: ReservationPolicy,
                    cancellationFeeSetting: CancellationFeeSetting,
                    changedAt: LocalDateTime): Details {

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

                val details = Details(studioId, usageTime, userCount, practiceType, rentalEquipments)
                details.validate(reservationPolicy, changedAt)
                return details
            }
        }
    }

    fun change(
            user: User.NonMember,
            studioId: StudioId,
            usageTime: UsageTime,
            userCount: UserCount,
            practiceType: PracticeType,
            rentalEquipments: RentalEquipments,
            reservationPolicy: ReservationPolicy,
            cancellationFeeSetting: CancellationFeeSetting,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime): Reservation {

        if (this.user !is User.NonMember)
            throw Exception()

        val changingDetails = details.change(studioId, usageTime, userCount, practiceType, rentalEquipments, reservationPolicy, cancellationFeeSetting, changedAt)
        return Reservation(reservationId, user, changingDetails, usageFee)
    }

    fun change(
            studioId: StudioId,
            usageTime: UsageTime,
            userCount: UserCount,
            practiceType: PracticeType,
            rentalEquipments: RentalEquipments,
            reservationPolicy: ReservationPolicy,
            cancellationFeeSetting: CancellationFeeSetting,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime): Reservation {

        val changingDetails = details.change(studioId, usageTime, userCount, practiceType, rentalEquipments, reservationPolicy, cancellationFeeSetting, changedAt)
        return Reservation(reservationId, user, changingDetails, usageFee)
    }

    fun isDuplicated(other: Reservation): Boolean {
        return this != other &&
                details.studioId == other.details.studioId &&
                details.usageTime.isOverlapping(other.details.usageTime)
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