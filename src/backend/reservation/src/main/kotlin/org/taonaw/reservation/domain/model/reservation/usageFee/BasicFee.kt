package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.reservation.UsageTime

data class BasicFee(val detailsList: List<UsageFeeDetails>) {

    companion object {
        fun nothing(): BasicFee {
            return BasicFee(listOf())
        }
    }

    fun usageTimes(): List<UsageTime> {
        return detailsList.map { it.usageTime }
    }
}