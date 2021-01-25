package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.shared.exception.Err

/**
 * 開始時間
 */
enum class StartTime(
        private val value: Int,
        private val startMinutes: Int) {

    ON_THE_HOUR(1, 0),
    ON_THE_HALF_HOUR(2, 30);

    fun validate(usageTime: UsageTime): Err? {
        if (usageTime.start.minute == startMinutes) return null
        return StartTimeErr()
    }
}