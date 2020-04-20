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

    public ChangeEquipmentResult handle(ChangeEquipmentCommand command) {
        var equipment = equipmentRepository.findBy(new EquipmentId(command.getEquipmentId())).orElseThrow();
        equipment.changeName(new EquipmentName(command.getName()));
        equipment.changeStocks(new EquipmentStocks(command.getStocks()));
        equipment.changeEquipmentCategory(new EquipmentCategoryId(command.getCategoryId()));
        equipmentRepository.update(equipment);

        return ChangeEquipmentResult.of(equipment);
    }
}
