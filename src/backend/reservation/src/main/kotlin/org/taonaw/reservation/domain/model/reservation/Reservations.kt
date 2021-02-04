package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservation.rentalEquipment.ReservedRentalEquipments
import org.taonaw.reservation.domain.shared.exception.Err

class Reservations(items: List<Reservation>) {
    val items: List<Reservation> = items.toList()

    fun toReservedRentalEquipments(reservation: Reservation): ReservedRentalEquipments {
        return ReservedRentalEquipments.create(items.map {
            if (it == reservation)
                reservation.details.rentalEquipments
            else
                it.details.rentalEquipments
        })
    }

    fun validateDuplicated(reservation: Reservation): Err? {
        if (items.any { it.isDuplicated(reservation) }) return ReservationDuplicatedErr()
        return null
    }
}