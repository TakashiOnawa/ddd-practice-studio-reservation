package org.taonaw.reservation.domain.model.reservation

import de.huxhorn.sulky.ulid.ULID

data class ReservationId(val value: String) {
    companion object {
        fun newId(): ReservationId {
            return ReservationId(ULID().nextULID())
        }
    }
}