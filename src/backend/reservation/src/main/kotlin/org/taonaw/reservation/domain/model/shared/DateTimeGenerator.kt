package org.taonaw.reservation.domain.model.shared

import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeGenerator {
    fun currentDateTime(): LocalDateTime = LocalDateTime.now()
    fun currentTime(): LocalTime = LocalTime.now()
}