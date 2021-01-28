package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.shared.TimeRange
import org.taonaw.reservation.domain.shared.exception.Err
import java.time.LocalTime

/**
 * 営業時間
 */
data class OpeningHour(
        override val start: LocalTime,
        override val end: LocalTime) : TimeRange {

    fun validate(usageTime: UsageTime): Err? {
        if (usageTime.isIn(this)) return null
        return OpeningHourErr()
    }
}