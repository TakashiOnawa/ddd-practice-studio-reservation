package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.shared.Fee

data class UsageFeeDetails(
        val usageTime: UsageTime,
        val fee: Fee,
        val feeType: FeeType) {
}