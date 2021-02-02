package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.PackFeeDetails
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFee

data class UsageFeeSetting(
        val basicFeeSetting: BasicFeeSetting,
        val packFeeSettings: List<PackFeeSetting>) {

    internal fun calculateUsageFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): UsageFee {
        val packFeeDetailsList = mutableListOf<PackFeeDetails>()
        for (packFeeSetting in packFeeSettings) {
            packFeeDetailsList.add(packFeeSetting.calculatePackFeeDetails(usageFeeCondition))
        }

        // [ ] TODO: 同じ時利用時間のパック料金が存在する場合は設定に不備があることになるため、料金計算は無効とする。

        // [ ] TODO: 利用時間からパック料金を適用した時間を除く。


        // [ ] TODO: 基本料金を計算する。

        // [ ] TODO: 同じ利用時間の基本料金が存在する場合は設定に不備があることになるため、料金計算は無効とする。

        // [ ] TODO: パック料金、基本料金に適用された利用時間の合計が全利用時間と一致しない場合は不備があることになるため、料金計算は無効とする。

        // [ ] TODO: レンタル機材料金を計算する。

        TODO("実装する")
    }

    fun isDeterminedByUserCount(): Boolean {
        TODO("実装する")
    }
}