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

    fun validate(reservationDetails: ReservationDetails, currentDateTime: LocalDateTime): ErrNotification {
        val errNotification = ErrNotification()
        errNotification.addErr(openingHour.validate(reservationDetails.usageTime))
        errNotification.addErr(startTime.validate(reservationDetails.usageTime))
        errNotification.addErr(acceptingReservationStartDate.validate(reservationDetails.usageTime, currentDateTime))
        errNotification.addErr(maxUserCount.validate(reservationDetails.userCount))
        errNotification.addErr(maxRentalEquipmentQuantities.validate(reservationDetails.rentalEquipments))
        return errNotification
    }
}
