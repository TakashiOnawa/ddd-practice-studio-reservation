package org.taonaw.reservation.domain.model.reservationPolicy

interface ReservationPolicyRepository {
    fun get(): ReservationPolicy
}