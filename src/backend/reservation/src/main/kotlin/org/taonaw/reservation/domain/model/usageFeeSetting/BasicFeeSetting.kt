package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.usageFee.BasicFee
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFeeDetails

data class BasicFeeSetting(private val usageFeeSpecifications: UsageFeeSpecifications) {

    fun calculateBasicFee(usageFeeConditions: List<UsageFeeCondition>): BasicFee {
        val usageFeeDetailsList = mutableListOf<UsageFeeDetails>()
        for (usageFeeCondition in usageFeeConditions) {
            usageFeeDetailsList.addAll(usageFeeSpecifications.calculateUsageFeeDetails(usageFeeCondition))
        }
        return BasicFee(usageFeeDetailsList)
    }
}