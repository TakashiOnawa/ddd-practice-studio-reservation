package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.ReservationDetails
import org.taonaw.reservation.domain.shared.exception.ErrNotification
import java.time.LocalDateTime

/**
 * 予約ポリシー
 */
class ReservationPolicy(
        private val openingHour: OpeningHour,
        private val startTime: StartTime,
        private val acceptingReservationStartDate: AcceptingReservationStartDate,
        private val maxUserCount: MaxUserCount,
        private val maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities) {

    internal fun validate(reservationDetails: ReservationDetails, currentDateTime: LocalDateTime): ErrNotification {
        return ErrNotification()
                .addErr(openingHour.validate(reservationDetails.usageTime))
                .addErr(startTime.validate(reservationDetails.usageTime))
                .addErr(acceptingReservationStartDate.validate(reservationDetails.usageTime, currentDateTime))
                .addErr(maxUserCount.validate(reservationDetails.userCount))
                .addErr(maxRentalEquipmentQuantities.validate(reservationDetails.rentalEquipments))
    }
}
