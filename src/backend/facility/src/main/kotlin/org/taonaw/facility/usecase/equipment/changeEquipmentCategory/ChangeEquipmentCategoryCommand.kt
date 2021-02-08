package org.taonaw.facility.usecase.equipment.changeEquipmentCategory

import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId
import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryName

data class ChangeEquipmentCategoryCommand(
        val equipmentCategoryId: EquipmentCategoryId,
        val equipmentCategoryName: EquipmentCategoryName) {
}