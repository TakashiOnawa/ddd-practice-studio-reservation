package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.*

data class UsageFeeSetting(
        val basicFeeSetting: BasicFeeSetting,
        val packFeeSettings: List<PackFeeSetting>) {

    internal fun calculateUsageFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): UsageFee {
        // パック料金を計算する。
        val packFee = calculatePackFee(usageFeeCondition) ?: return UsageFee.nothing()
        // 基本料金を計算する。
        val basicFee = calculateBasicFee(usageFeeCondition, packFee) ?: return UsageFee.nothing()

        // TODO: パック料金、基本料金に適用された利用時間の合計が全利用時間と一致しない場合は不備があることになるため、料金計算は無効とする。

        // レンタル機材料金を計算する。
        val rentalEquipmentFee = calculateRentalEquipmentFee(usageFeeCondition, equipments)

        return UsageFee(basicFee, packFee, rentalEquipmentFee)
    }

    fun isDeterminedByUserCount(): Boolean {
        TODO("実装する")
    }

    private fun calculatePackFee(usageFeeCondition: UsageFeeCondition): PackFee? {
        val packFeeDetailsList = mutableListOf<PackFeeDetails>()
        for (packFeeSetting in packFeeSettings) {
            packFeeDetailsList.add(packFeeSetting.calculatePackFeeDetails(usageFeeCondition))
        }
        val packFee = PackFee(packFeeDetailsList)

        // パック料金が適用された利用時間に重複がある場合は利用金設定に不備があることになるため、料金計算は無効とする。
        if (packFee.hasOverlappingUsageTimeFees())
            return null

        return packFee
    }

    private fun calculateBasicFee(usageFeeCondition: UsageFeeCondition, packFee: PackFee): BasicFee? {
        // パック料金が適用された利用時間を除いた利用時間で基本料金を計算する。
        val basicFee = basicFeeSetting.calculateBasicFee(usageFeeCondition.exceptUsageTimes(packFee.usageTimes()))

        // 基本料金が適用された利用時間に重複がある場合は利用料金設定に不備があることになるため、料金計算は無効とする。
        if (basicFee.hasOverlappingUsageTimeFees())
            return null

        return basicFee
    }

    private fun calculateRentalEquipmentFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): RentalEquipmentFee {
        TODO("実装する。")
    }
}