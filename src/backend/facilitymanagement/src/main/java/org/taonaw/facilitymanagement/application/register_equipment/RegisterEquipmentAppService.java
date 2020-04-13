package org.taonaw.facilitymanagement.application.register_equipment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.equipment.*;
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

        return RegisterEquipmentResult.builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .build();
    }
}
