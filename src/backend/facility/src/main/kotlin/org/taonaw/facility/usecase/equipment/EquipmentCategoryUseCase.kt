package org.taonaw.facility.usecase.equipment

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategory
import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryRepository
import org.taonaw.facility.usecase.EquipmentCategoryNotFound
import org.taonaw.facility.usecase.equipment.changeEquipmentCategory.ChangeEquipmentCategoryCommand
import org.taonaw.facility.usecase.equipment.registerEquipmentCategory.RegisterEquipmentCategoryCommand

@Component
class EquipmentCategoryUseCase(
        val equipmentCategoryRepository: EquipmentCategoryRepository) {

    fun handle(command: RegisterEquipmentCategoryCommand) {
        val equipmentCategory = EquipmentCategory.create(command.equipmentCategoryName)

        equipmentCategoryRepository.save(equipmentCategory)
    }

    fun handle (command: ChangeEquipmentCategoryCommand) {
        var equipmentCategory = equipmentCategoryRepository.findBy(command.equipmentCategoryId) ?: throw EquipmentCategoryNotFound()

        equipmentCategory = equipmentCategory.change(command.equipmentCategoryName)

        equipmentCategoryRepository.save(equipmentCategory)
    }
}