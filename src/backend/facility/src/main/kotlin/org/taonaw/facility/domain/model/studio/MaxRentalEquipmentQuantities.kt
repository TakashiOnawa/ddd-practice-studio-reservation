package org.taonaw.facility.domain.model.studio

import org.taonaw.facility.domain.shared.exception.Err

class MaxRentalEquipmentQuantities(items: List<MaxRentalEquipmentQuantity>) {
    val items: List<MaxRentalEquipmentQuantity> = items.toList()

    fun validateDuplicated(): Err? {
        val errorItems = items
                .filter { items.any { other -> it.isDuplicated(other) } }
                .map { it.equipmentCategoryId }

        if (errorItems.isEmpty()) return MaxRentalEquipmentQuantityDuplicatedErr(errorItems)
        return null
    }
}