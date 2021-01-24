package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeCondition
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import java.time.LocalDateTime

/**
 * 予約
 */
class Reservation private constructor(
        val reservationId: ReservationId,
        val user: User,
        val details: ReservationDetails,
        val usageFee: UsageFee) {

    companion object {
        fun create(
                user: User,
                details: ReservationDetails,
                reservationPolicy: ReservationPolicy,
                usageFeeSetting: UsageFeeSetting,
                equipments: Equipments,
                reservedAt: LocalDateTime) : Reservation {

            details.validate(reservationPolicy, reservedAt)

            val usageFee = usageFeeSetting.calculateUsageFee(
                    UsageFeeCondition(details.studioId, details.usageTime, details.userCount, details.practiceType, details.rentalEquipments),
                    equipments)

            return Reservation(ReservationId.newId(), user, details, usageFee)
        }
    }

    fun change(
            changingUser: User.NonMember,
            changingDetails: ReservationDetails,
            reservationPolicy: ReservationPolicy,
            cancellationFeeSetting: CancellationFeeSetting,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime): Reservation {

        if (this.user !is User.NonMember)
            throw Exception()

        this.details.validateChanging(changingDetails, cancellationFeeSetting, changedAt)
        changingDetails.validate(reservationPolicy, changedAt)

        val changingUsageFee = usageFeeSetting.calculateUsageFee(
                UsageFeeCondition(changingDetails.studioId, changingDetails.usageTime, changingDetails.userCount, changingDetails.practiceType, changingDetails.rentalEquipments),
                equipments)

        return Reservation(reservationId, changingUser, changingDetails, changingUsageFee)
    }

    fun change(
            changingDetails: ReservationDetails,
            reservationPolicy: ReservationPolicy,
            cancellationFeeSetting: CancellationFeeSetting,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime): Reservation {

        this.details.validateChanging(changingDetails, cancellationFeeSetting, changedAt)
        changingDetails.validate(reservationPolicy, changedAt)

        val changingUsageFee = usageFeeSetting.calculateUsageFee(
                UsageFeeCondition(changingDetails.studioId, changingDetails.usageTime, changingDetails.userCount, changingDetails.practiceType, changingDetails.rentalEquipments),
                equipments)

        return Reservation(reservationId, user, changingDetails, changingUsageFee)
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