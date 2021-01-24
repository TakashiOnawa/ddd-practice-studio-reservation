package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.model.reservation.rentalEquipment.RentalEquipments
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

    fun validateOpeningHour(usageTime: UsageTime) {
        if (!openingHour.isSatisfiedBy(usageTime))
            throw Exception()
    }

    fun validateStartTime(usageTime: UsageTime) {
        if (!startTime.isSatisfiedBy(usageTime))
            throw Exception()
    }

    fun validateAcceptingReservationStartDate(usageTime: UsageTime, currentDateTime: LocalDateTime) {
        if (!acceptingReservationStartDate.isSatisfiedBy(usageTime, currentDateTime))
            throw Exception()
    }

    fun validateMaxUserCount(userCount: UserCount) {
        if (!maxUserCount.isSatisfiedBy(userCount))
            throw Exception()
    }

    fun validateMaxRentalEquipmentQuantity(rentalEquipments: RentalEquipments) {
        if (!maxRentalEquipmentQuantities.isSatisfiedBy(rentalEquipments))
            throw Exception()
    }
}
