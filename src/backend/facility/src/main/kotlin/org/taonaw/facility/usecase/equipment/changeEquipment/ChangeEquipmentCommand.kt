package org.taonaw.facility.usecase.equipment.changeEquipment

import org.taonaw.facility.domain.model.equipment.EquipmentId
import org.taonaw.facility.domain.model.equipment.EquipmentName
import org.taonaw.facility.domain.model.equipment.EquipmentRentalFee
import org.taonaw.facility.domain.model.equipment.EquipmentStocks
import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId

data class ChangeEquipmentCommand(
        val equipmentId: EquipmentId,
        val equipmentName: EquipmentName,
        val equipmentCategoryId: EquipmentCategoryId,
        val equipmentRentalFee: EquipmentRentalFee,
        val equipmentStocks: EquipmentStocks) {
}