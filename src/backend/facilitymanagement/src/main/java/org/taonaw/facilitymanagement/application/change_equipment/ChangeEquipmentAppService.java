package org.taonaw.facilitymanagement.application.change_equipment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentId;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentName;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentStocks;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;

@Service
@AllArgsConstructor
public class ChangeEquipmentAppService {
    @Autowired
    private final IEquipmentRepository equipmentRepository;

    public ChangeEquipmentResponse handle(ChangeEquipmentRequest request) {
        var equipment = equipmentRepository.findBy(new EquipmentId(request.getEquipmentId())).orElseThrow();
        equipment.changeName(new EquipmentName(request.getName()));
        equipment.changeStocks(new EquipmentStocks(request.getStocks()));
        equipment.changeEquipmentCategory(new EquipmentCategoryId(request.getCategoryId()));
        equipmentRepository.update(equipment);

        return ChangeEquipmentResponse.builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .name(equipment.getName().getValue())
                .stocks(equipment.getStocks().getValue())
                .categoryId(equipment.getCategoryId().getValue())
                .build();
    }
}
