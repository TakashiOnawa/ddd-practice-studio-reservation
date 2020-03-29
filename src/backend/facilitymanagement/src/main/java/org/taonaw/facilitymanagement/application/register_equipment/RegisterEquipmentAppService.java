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

    public RegisterEquipmentResponse handle(RegisterEquipmentRequest request) {
        var equipment = Equipment.newEquipment(
                new EquipmentName(request.getName()),
                new EquipmentCategoryId(request.getCategoryId()),
                new EquipmentStocks(request.getStocks()));

        equipmentRepository.add(equipment);

        return RegisterEquipmentResponse.builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .build();
    }
}
