package org.taonaw.reservation.domain.model.reservation

import java.time.LocalDateTime

data class UsageDateTime(
        val startDataTime: LocalDateTime,
        val endDateTime: LocalDateTime) {
}
