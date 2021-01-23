package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee

data class UsageFeeSetting(
        val basicFeeSetting: BasicFeeSetting,
        val packFeeSetting: PackFeeSetting) {

    fun calculateUsageFee(condition: UsageFeeCondition, equipments: Equipments): UsageFee {
        TODO("実装する")
    }
}