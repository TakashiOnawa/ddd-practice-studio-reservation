package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.usageFee.BasicFee

data class BasicFeeSetting(private val usageFeeSpecifications: UsageFeeSpecifications) {

    fun calculateBasicFee(usageFeeConditions: List<UsageFeeCondition>): BasicFee {
        return BasicFee(usageFeeConditions.flatMap { usageFeeSpecifications.calculateUsageFeeDetails(it) })
    }
}