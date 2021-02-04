package org.taonaw.reservation.domain.model.cancellationFeeSetting

import org.taonaw.reservation.domain.model.reservation.UsageTime
import java.time.LocalDateTime

class CancellationFeeSetting(rates: List<CancellationFeeRate>) {
    private val rates: List<CancellationFeeRate> = rates.sortedByDescending { it.daysAgo }

    fun isCancellationFeeChanged(usageTime: UsageTime, dateTime: LocalDateTime): Boolean {
        return rates.any { it.isApplied(usageTime, dateTime.toLocalDate()) }
    }
}