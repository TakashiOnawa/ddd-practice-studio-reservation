package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UserCount

/**
 * 最大利用人数
 */
data class MaxUserCount(private val value: Int) {

    fun isSatisfiedBy(userCount: UserCount): Boolean {
        return userCount.value <= value
    }
}