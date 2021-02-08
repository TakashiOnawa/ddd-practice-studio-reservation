package org.taonaw.facility.usecase.equipment

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.equipment.Equipment
import org.taonaw.facility.domain.model.equipment.EquipmentRepository
import org.taonaw.facility.usecase.EquipmentNotFound
import org.taonaw.facility.usecase.equipment.changeEquipment.ChangeEquipmentCommand
import org.taonaw.facility.usecase.equipment.registerEquipment.RegisterEquipmentCommand

@Component
class EquipmentUseCase(
        val equipmentRepository: EquipmentRepository) {

    fun handle(command: RegisterEquipmentCommand) {
        val equipment = Equipment.create(
                command.equipmentName,
                command.equipmentCategoryId,
                command.equipmentRentalFee,
                command.equipmentStocks)

        equipmentRepository.save(equipment)
    }

    fun handle(command: ChangeEquipmentCommand) {
        var equipment = equipmentRepository.findBy(command.equipmentId) ?: throw EquipmentNotFound()

        equipment = equipment.change(
                command.equipmentName,
                command.equipmentCategoryId,
                command.equipmentRentalFee,
                command.equipmentStocks)

        equipmentRepository.save(equipment)
    }
}