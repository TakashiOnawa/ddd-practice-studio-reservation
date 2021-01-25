package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSetting
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicy
import org.taonaw.reservation.domain.model.shared.StudioId
import java.time.LocalDateTime

/**
 * 予約内容
 */
data class ReservationDetails(
        val studioId: StudioId,
        val usageTime: UsageTime,
        val userCount: UserCount,
        val practiceType: PracticeType,
        val rentalEquipments: RentalEquipments) {

    internal fun validate(reservationPolicy: ReservationPolicy, currentDateTime: LocalDateTime) {
        reservationPolicy.validateOpeningHour(usageTime)
        reservationPolicy.validateStartTime(usageTime)
        reservationPolicy.validateAcceptingReservationStartDate(usageTime, currentDateTime)
        reservationPolicy.validateMaxUserCount(userCount)
        reservationPolicy.validateMaxRentalEquipmentQuantity(rentalEquipments)
    }

    internal fun validateChanging(
            changingDetails: ReservationDetails,
            cancellationFeeSetting: CancellationFeeSetting,
            changedAt: LocalDateTime) {

        val chargedCancellationFee = cancellationFeeSetting.chargedCancellationFee(usageTime, changedAt)

        // TODO: 変更判定
        if (this.studioId != changingDetails.studioId && chargedCancellationFee) {
            throw Exception()
        }
        if (this.usageTime != usageTime && chargedCancellationFee) {
            throw Exception()
        }
    }
}