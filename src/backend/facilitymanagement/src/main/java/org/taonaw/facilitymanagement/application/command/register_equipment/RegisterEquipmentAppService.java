package org.taonaw.facilitymanagement.application.command.register_equipment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentName;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentStocks;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;

@Service
@AllArgsConstructor
public class RegisterEquipmentAppService {
    @Autowired
    private final IEquipmentRepository equipmentRepository;

    public RegisterEquipmentResult handle(RegisterEquipmentCommand command) {
        var equipment = Equipment.newEquipment(
                new EquipmentName(command.getName()),
                new EquipmentCategoryId(command.getCategoryId()),
                new EquipmentStocks(command.getStocks()));

        equipmentRepository.add(equipment);

        return RegisterEquipmentResult.of(equipment);
    }
}
