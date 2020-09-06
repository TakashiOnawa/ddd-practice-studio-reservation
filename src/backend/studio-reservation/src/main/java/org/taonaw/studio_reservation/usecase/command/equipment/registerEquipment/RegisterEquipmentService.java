package org.taonaw.studio_reservation.usecase.command.equipment.registerEquipment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.equipment.Equipment;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentRepository;

@AllArgsConstructor
public class RegisterEquipmentService {
    @Autowired
    private final EquipmentRepository equipmentRepository;

    public void handle(RegisterEquipmentCommand command) {
        var equipment = Equipment.create(
                command.getName(),
                command.getCategoryId(),
                command.getStockCount());

        equipmentRepository.add(equipment);
    }
}
