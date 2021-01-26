package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationChangingPolicy
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeCondition
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSetting
import java.time.LocalDateTime

/**
 * 予約
 */
class Reservation private constructor(
        val reservationId: ReservationId,
        val details: ReservationDetails,
        val usageFee: UsageFee) {

    companion object {
        fun create(
                details: ReservationDetails,
                reservationPolicy: ReservationPolicy,
                usageFeeSetting: UsageFeeSetting,
                equipments: Equipments,
                reservedAt: LocalDateTime) : Reservation {

            reservationPolicy.validate(details, reservedAt).throwIfHasErrs("予約内容に不備があります。")

            val usageFee = usageFeeSetting.calculateUsageFee(UsageFeeCondition.from(details), equipments)

            return Reservation(ReservationId.newId(), details, usageFee)
        }
    }

    fun change(
            changingDetails: ReservationDetails,
            reservationPolicy: ReservationPolicy,
            reservationChangingPolicy: ReservationChangingPolicy,
            usageFeeSetting: UsageFeeSetting,
            equipments: Equipments,
            changedAt: LocalDateTime): Reservation {

        reservationChangingPolicy.validate(details, changingDetails, changedAt).throwIfHasErrs("予約内容に不備があります。")
        reservationPolicy.validate(changingDetails, changedAt).throwIfHasErrs("予約内容に不備があります。")

        val changingUsageFee = usageFeeSetting.calculateUsageFee(UsageFeeCondition.from(changingDetails), equipments)

        return Reservation(reservationId, changingDetails, changingUsageFee)
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