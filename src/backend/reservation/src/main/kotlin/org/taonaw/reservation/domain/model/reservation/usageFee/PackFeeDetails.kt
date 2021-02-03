package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.reservation.UsageTime

data class PackFeeDetails(
        val packName: String,
        val detailsList: List<UsageFeeDetails>) {

    fun usageTimes(): List<UsageTime> {
        return detailsList.map { it.usageTime }
    }
}