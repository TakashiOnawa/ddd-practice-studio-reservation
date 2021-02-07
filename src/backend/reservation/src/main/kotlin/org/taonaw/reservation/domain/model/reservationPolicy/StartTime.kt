package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.shared.exception.Err

/**
 * 開始時間
 */
data class StartTime(private val value: Int) {
    fun validate(usageTime: UsageTime): Err? {
        if (usageTime.start.minute == value) return null
        return StartTimeErr()
    }
}