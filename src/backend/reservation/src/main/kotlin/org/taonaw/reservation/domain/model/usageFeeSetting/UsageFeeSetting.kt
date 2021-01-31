package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.TotalUsageFee.TotalUsageFee

data class UsageFeeSetting(
        val basicFeeSetting: BasicFeeSetting,
        val packFeeSetting: PackFeeSetting) {

    internal fun calculateUsageFee(calculationCondition: UsageFeeCalculationCondition, equipments: Equipments): TotalUsageFee {




        TODO("実装する")
    }

    fun isDeterminedByUserCount(): Boolean {
        TODO("実装する")
        return true
    }
}