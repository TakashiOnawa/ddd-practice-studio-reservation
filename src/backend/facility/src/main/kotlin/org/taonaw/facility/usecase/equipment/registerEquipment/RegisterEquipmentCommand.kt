package org.taonaw.facility.usecase.equipment.registerEquipment

import org.taonaw.facility.domain.model.equipment.EquipmentName
import org.taonaw.facility.domain.model.equipment.EquipmentRentalFee
import org.taonaw.facility.domain.model.equipment.EquipmentStocks
import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId

data class RegisterEquipmentCommand(
        val equipmentName: EquipmentName,
        val equipmentCategoryId: EquipmentCategoryId,
        val equipmentRentalFee: EquipmentRentalFee,
        val equipmentStocks: EquipmentStocks) {
}