package org.taonaw.reservation.domain.model.reservation.usageFee

import org.taonaw.reservation.domain.model.reservation.UsageTime

data class PackFee(val packFeeDetailsList: List<PackFeeDetails>) {

    companion object {
        fun nothing(): PackFee {
            return PackFee(listOf())
        }
    }

    fun usageTimes(): List<UsageTime> {
        return packFeeDetailsList.flatMap { it.usageTimes() }
    }
}