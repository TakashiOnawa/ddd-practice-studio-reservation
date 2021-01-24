package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.equipment.Equipments

class Reservations private constructor(items: List<Reservation>) {
    val items = items.toList()

    fun validateDuplicated(reservation: Reservation) {
        if (items.any { it.isDuplicated(reservation) })
            throw Exception()
    }

    fun validateUsageEquipmentsOutOfStocks(reservation: Reservation, equipments: Equipments) {
        // TODO: 実装する
    }
}