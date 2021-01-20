package org.taonaw.reservation.domain.model.reservation

interface ReservationRepository {
    fun lock()
    fun findBy(reservationId: ReservationId): Reservation?
    fun findBy(usageTime: UsageTime): Reservations
    fun save(reservation: Reservation)
}