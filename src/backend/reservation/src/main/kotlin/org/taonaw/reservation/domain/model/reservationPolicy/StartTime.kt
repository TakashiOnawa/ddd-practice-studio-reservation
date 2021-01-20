package org.taonaw.reservation.domain.model.reservationPolicy

enum class StartTime(val value: Int, val startMinutes: Int) {
    ON_THE_HOUR(1, 0),
    ON_THE_HALF_HOUR(2, 30)
}