package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.RentalEquipments
import org.taonaw.reservation.domain.model.reservation.UsageDateTime
import org.taonaw.reservation.domain.model.reservation.UserCount
import java.time.LocalDateTime

class ReservationPolicy {
    fun validateReservableDateTime(usageDateTime: UsageDateTime) {

    }

    fun validateStartTime(usageDateTime: UsageDateTime) {

    }

    fun validateReservationStartDateTime(usageDateTime: UsageDateTime, currentDateTime: LocalDateTime) {

    }

    fun validateUserMaxCount(userCount: UserCount) {

    }

    fun validateRentalEquipmentMaxQuantity(rentalEquipments: RentalEquipments) {

    }
}
