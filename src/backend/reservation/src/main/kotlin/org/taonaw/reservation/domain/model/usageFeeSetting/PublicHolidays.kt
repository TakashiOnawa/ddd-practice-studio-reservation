package org.taonaw.reservation.domain.model.usageFeeSetting

import java.time.LocalDate

class PublicHolidays(items: List<LocalDate>) {
    private val items: List<LocalDate> = items.toList()

    fun isPublicHoliday(date: LocalDate): Boolean {
        return items.any { it == date }
    }
}