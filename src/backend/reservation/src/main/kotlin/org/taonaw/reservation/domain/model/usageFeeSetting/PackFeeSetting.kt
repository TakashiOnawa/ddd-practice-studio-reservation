package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.usageFee.PackFeeDetails

data class PackFeeSetting(
        private val packName: String,
        private val usageFeeSpecifications: UsageFeeSpecifications) {

    fun calculatePackFeeDetails(usageFeeCondition: UsageFeeCondition): PackFeeDetails {
        return PackFeeDetails(packName, usageFeeSpecifications.calculateUsageFeeDetails(usageFeeCondition))
    }
}