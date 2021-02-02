package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.usageFee.BasicFee

data class BasicFeeSetting(private val usageFeeSpecifications: UsageFeeSpecifications) {

    fun calculateBasicFee(usageFeeCondition: UsageFeeCondition): BasicFee {
        return BasicFee(usageFeeSpecifications.calculateUsageFeeDetails(usageFeeCondition))
    }
}