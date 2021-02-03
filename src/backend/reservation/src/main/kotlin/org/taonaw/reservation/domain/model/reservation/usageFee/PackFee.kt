package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.reservation.UsageTime

data class PackFee(val packFeeDetailsList: List<PackFeeDetails>) {

    companion object {
        fun nothing(): PackFee {
            return PackFee(listOf())
        }
    }

    fun hasOverlappingUsageTimeFees(): Boolean {
        return packFeeDetailsList
                .flatMap { it.detailsList }
                .any { packFeeDetails -> packFeeDetailsList
                        .flatMap { it.detailsList }
                        .count { it.usageTime.isOverlapping(packFeeDetails.usageTime) } > 1 }
    }

    fun usageTimes(): List<UsageTime> {
        return packFeeDetailsList.flatMap { it.usageTimes() }
    }
}