package org.taonaw.facility.domain.model.studio

import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId

class MaxRentalEquipmentQuantity(
        val equipmentCategoryId: EquipmentCategoryId,
        val quantity: Int) {

    companion object {
        const val QUANTITY_MIN = 0
        const val QUANTITY_MAX = 99
    }

    init {
        require(quantity in QUANTITY_MIN.. QUANTITY_MAX) {
            "最大レンタル機材数は $QUANTITY_MIN 個以上 $QUANTITY_MAX 個以下でなければなりません。"
        }
    }

    fun isDuplicated(other: MaxRentalEquipmentQuantity): Boolean {
        return this !== other && equipmentCategoryId == other.equipmentCategoryId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MaxRentalEquipmentQuantity

        if (equipmentCategoryId != other.equipmentCategoryId) return false

        return true
    }

    override fun hashCode(): Int {
        return equipmentCategoryId.hashCode()
    }
}