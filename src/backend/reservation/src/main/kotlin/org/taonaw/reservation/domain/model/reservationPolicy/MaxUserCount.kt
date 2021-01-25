package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UserCount
import org.taonaw.reservation.domain.shared.exception.Err

/**
 * 最大利用人数
 */
data class MaxUserCount(private val value: Int) {

    fun validate(userCount: UserCount): Err? {
        if (userCount.value <= value) return null
        return MaxUserCountErr()
    }
}