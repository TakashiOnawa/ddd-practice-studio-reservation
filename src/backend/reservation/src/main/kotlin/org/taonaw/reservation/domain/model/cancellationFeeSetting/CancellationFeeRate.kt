package org.taonaw.reservation.domain.model.cancellationFeeSetting

import org.taonaw.reservation.domain.model.reservation.UsageTime
import java.time.LocalDate

data class CancellationFeeRate(
        val daysAgo: Int,
        val rate: Double) {

    fun isApplied(usageTime: UsageTime, date: LocalDate): Boolean {
        val appliedDate = usageTime.start.toLocalDate().minusDays(daysAgo.toLong())
        return date >= appliedDate
    }
}