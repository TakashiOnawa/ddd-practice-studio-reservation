package org.taonaw.reservation.domain.model.reservationPolicy

data class AcceptingReservationStartDate(
        val dateValue: Int,
        val dateType: AcceptingReservationStartDateType) {
}