package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.reservationPolicy.MaxUserCount

data class UserCount(val value: Int) {

    fun satisfy(maxUserCount: MaxUserCount): Boolean {
        return value <= maxUserCount.value
    }
}