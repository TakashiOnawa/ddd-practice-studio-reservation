package org.taonaw.reservation.domain.model.reservation

class Reservations private constructor(items: List<Reservation>) {
    private val items = items.toList()

    fun validateDuplicated(reservation: Reservation) {
        if (items.any { it.isDuplicated(reservation) })
            throw Exception()
    }
}