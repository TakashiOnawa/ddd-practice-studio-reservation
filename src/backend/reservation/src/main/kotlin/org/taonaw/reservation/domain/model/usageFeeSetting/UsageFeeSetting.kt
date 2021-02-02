package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee

data class UsageFeeSetting(
        val basicFeeSetting: BasicFeeSetting,
        val packFeeSettings: List<PackFeeSetting>) {

    internal fun calculateUsageFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): UsageFee {
        for (packFeeSetting in packFeeSettings) {

        }

        TODO("実装する")
    }

    fun isDeterminedByUserCount(): Boolean {
        TODO("実装する")
    }
}