package org.taonaw.facility.domain.model.cancellationFee

import org.taonaw.facility.domain.shared.exception.Err

class CancellationFeeRates(items: List<CancellationFeeRate>) {
    val items: List<CancellationFeeRate> = items.sortedByDescending { it.daysAgo }

    fun validateDuplicated(): Err? {
        val errorItems = items.filter { items.any { other -> it.isDuplicated(other) } }
        if (errorItems.isNotEmpty()) return CancellationFeeRateDuplicatedErr(CancellationFeeRates(errorItems))
        return null
    }

    fun validateRateNotRise(): Err? {
        var currentRate = 0.0
        for (item in items) {
            if (currentRate > item.rate) {
                return CancellationFeeRateNotRiseError()
            }
            currentRate = item.rate
        }
        return null
    }
}