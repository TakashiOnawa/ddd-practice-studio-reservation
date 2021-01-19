package org.taonaw.reservation.domain.model.reservation

interface ReservationRepository {
    fun findBy(reservationId: ReservationId): Reservation?
    fun findBy(usageDateTime: UsageDateTime): Reservations
    fun save(reservation: Reservation)
}