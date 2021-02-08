package org.taonaw.facility.usecase.equipment.registerEquipmentCategory

import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryName

data class RegisterEquipmentCategoryCommand(val equipmentCategoryName: EquipmentCategoryName) {
}