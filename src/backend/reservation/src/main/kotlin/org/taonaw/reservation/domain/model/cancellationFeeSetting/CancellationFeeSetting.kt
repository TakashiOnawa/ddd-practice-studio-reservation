package org.taonaw.reservation.domain.model.cancellationFeeSetting

import org.taonaw.reservation.domain.model.reservation.UsageTime
import java.time.LocalDateTime

class CancellationFeeSetting {
    fun chargedCancellationFee(usageTime: UsageTime, changedAt: LocalDateTime): Boolean {
        // TODO: 実装する
        return false
    }
}