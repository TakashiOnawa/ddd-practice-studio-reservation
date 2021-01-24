package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservation.rentalEquipment.ReservedRentalEquipments

class Reservations private constructor(items: List<Reservation>) {
    val items: List<Reservation> = items.toList()

    fun toReservedRentalEquipments(reservation: Reservation): ReservedRentalEquipments {
        return ReservedRentalEquipments.create(items.map {
            if (it == reservation)
                reservation.details.rentalEquipments
            else
                it.details.rentalEquipments
        })
    }

    fun validateDuplicated(reservation: Reservation) {
        if (items.any { it.isDuplicated(reservation) })
            throw Exception()
    }
}