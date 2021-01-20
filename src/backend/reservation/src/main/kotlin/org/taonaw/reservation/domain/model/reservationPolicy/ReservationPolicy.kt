package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.RentalEquipments
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.model.shared.EquipmentId
import java.time.LocalDateTime

class ReservationPolicy(
        private val openingHour: OpeningHour,
        private val startTime: StartTime,
        private val acceptingReservationStartDate: AcceptingReservationStartDate,
        private val masUserCount: MaxUserCount,
        private val maxRentalEquipmentQuantities: Map<EquipmentId, MaxRentalEquipmentQuantity>) {

    fun validateOpeningHour(usageTime: UsageTime) {
        if (!usageTime.satisfy(openingHour))
            throw Exception()
    }

    fun validateStartTime(usageTime: UsageTime) {
        if (!usageTime.satisfy(startTime))
            throw Exception()
    }

    fun validateAcceptingReservationStartDate(usageTime: UsageTime, currentDateTime: LocalDateTime) {
        if (!usageTime.satisfy(acceptingReservationStartDate, currentDateTime))
            throw Exception()
    }

    fun validateMaxUserCount(userCount: UserCount) {
        if (!userCount.satisfy(masUserCount))
            throw Exception()
    }

    fun validateMaxRentalEquipmentQuantity(rentalEquipments: RentalEquipments) {
        rentalEquipments.notSatisfyEquipments(maxRentalEquipmentQuantities).let {
            if (it.isNotEmpty())
                throw Exception()
        }
    }
}
