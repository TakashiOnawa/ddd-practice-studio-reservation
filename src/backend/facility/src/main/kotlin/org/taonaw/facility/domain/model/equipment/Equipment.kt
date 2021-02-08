package org.taonaw.facility.domain.model.equipment

import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId

class Equipment private constructor(
        val equipmentId: EquipmentId,
        val equipmentName: EquipmentName,
        val equipmentCategoryId: EquipmentCategoryId,
        val equipmentRentalFee: EquipmentRentalFee,
        val equipmentStocks: EquipmentStocks) {

    companion object {
        fun create(
                equipmentName: EquipmentName,
                equipmentCategoryId: EquipmentCategoryId,
                equipmentRentalFee: EquipmentRentalFee,
                equipmentStocks: EquipmentStocks): Equipment {

            return Equipment(EquipmentId.newId(), equipmentName, equipmentCategoryId, equipmentRentalFee, equipmentStocks)
        }
    }

    fun change(
            equipmentName: EquipmentName,
            equipmentCategoryId: EquipmentCategoryId,
            equipmentRentalFee: EquipmentRentalFee,
            equipmentStocks: EquipmentStocks): Equipment {

        return Equipment(equipmentId, equipmentName, equipmentCategoryId, equipmentRentalFee, equipmentStocks)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Equipment

        if (equipmentId != other.equipmentId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentId.hashCode()
    }
}