package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.equipment.Equipments
import org.taonaw.reservation.domain.model.reservation.usageFee.*

class UsageFeeSetting(
        private val basicFeeSetting: BasicFeeSetting,
        private val packFeeSettings: List<PackFeeSetting>) {

    internal fun calculateUsageFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): UsageFee {
        val packFee = calculatePackFee(usageFeeCondition)
        val basicFee = calculateBasicFee(usageFeeCondition, packFee)

        if (isInvalidFeeApplicableUsageTimes(usageFeeCondition, packFee, basicFee))
            return UsageFee.nothing()

        val rentalEquipmentFee = calculateRentalEquipmentFee(usageFeeCondition, equipments)

        return UsageFee(basicFee, packFee, rentalEquipmentFee)
    }

    fun isDeterminedByUserCount(): Boolean {
        TODO("実装する")
    }

    private fun calculatePackFee(usageFeeCondition: UsageFeeCondition): PackFee {
        val packFeeDetailsList = packFeeSettings.map { it.calculatePackFeeDetails(usageFeeCondition) }
        return PackFee(packFeeDetailsList)
    }

    private fun calculateBasicFee(usageFeeCondition: UsageFeeCondition, packFee: PackFee): BasicFee {
        // パック料金が適用された利用時間を除いた利用時間で基本料金を計算する。
        return basicFeeSetting.calculateBasicFee(usageFeeCondition.exceptUsageTimes(packFee.usageTimes()))
    }

    private fun calculateRentalEquipmentFee(usageFeeCondition: UsageFeeCondition, equipments: Equipments): RentalEquipmentFee {
        val rentalEquipmentFeeDetailsList = usageFeeCondition.rentalEquipments.items.map {
            val equipment = equipments.findBy(it.equipmentId) ?: throw NoSuchElementException("機材が存在しません。equipmentId=${it.equipmentId.value}")
            RentalEquipmentFeeDetails(
                    it.equipmentId,
                    it.quantity,
                    UsageFeeDetails(usageFeeCondition.usageTime, equipment.rentalFee, FeeType.HOURLY))
        }
        return RentalEquipmentFee(rentalEquipmentFeeDetailsList)
    }

    private fun isInvalidFeeApplicableUsageTimes(usageFeeCondition: UsageFeeCondition, packFee: PackFee, basicFee: BasicFee): Boolean {
        val feeApplicableUsageTimes = packFee.usageTimes() + basicFee.usageTimes()

        // 料金適用対象の利用時間に重複がある場合は料金設定に不備があることになるため、料金計算は無効とする。
        if (feeApplicableUsageTimes.any { it.overlappingCount(feeApplicableUsageTimes) > 1 })
            return true

        // 料金適用対象の利用時間の合計が実際の利用時間と一致しない場合は料金設定に不備があることになるため、料金計算は無効とする。
        if (usageFeeCondition.usageTime.except(feeApplicableUsageTimes).isNotEmpty())
            return true

        return false
    }
}