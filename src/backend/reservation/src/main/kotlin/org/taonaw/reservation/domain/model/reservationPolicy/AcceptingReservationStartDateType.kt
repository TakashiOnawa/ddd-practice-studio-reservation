package org.taonaw.reservation.domain.model.reservationPolicy

enum class AcceptingReservationStartDateType(val value: Int) {
    DAYS_AGO(1),
    WEEKS_AGO(2),
    MONTHS_AGO(3)
}