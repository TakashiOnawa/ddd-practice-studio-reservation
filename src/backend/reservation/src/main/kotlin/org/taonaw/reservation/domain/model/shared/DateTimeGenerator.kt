package org.taonaw.reservation.domain.model.shared

import java.time.LocalDateTime
import java.time.LocalTime

open class DateTimeGenerator {
    open fun currentDateTime(): LocalDateTime = LocalDateTime.now()
    open fun currentTime(): LocalTime = LocalTime.now()
}