package org.taonaw.facility.domain.model.equipmentCategory

class EquipmentCategory private constructor(
        val equipmentCategoryId: EquipmentCategoryId,
        val equipmentCategoryName: EquipmentCategoryName) {

    companion object {
        fun create(equipmentCategoryName: EquipmentCategoryName): EquipmentCategory {
            return EquipmentCategory(EquipmentCategoryId.newId(), equipmentCategoryName)
        }
    }

    fun change(equipmentCategoryName: EquipmentCategoryName): EquipmentCategory {
        return EquipmentCategory(equipmentCategoryId, equipmentCategoryName)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EquipmentCategory

        if (equipmentCategoryId != other.equipmentCategoryId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentCategoryId.hashCode()
    }
}