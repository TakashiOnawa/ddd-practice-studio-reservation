package org.taonaw.facilitymanagement.application.change_equipmentcategory;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryName;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;

@Service
@AllArgsConstructor
public class ChangeEquipmentCategoryAppService {
    @Autowired
    private final IEquipmentCategoryRepository equipmentCategoryRepository;

    public ChangeEquipmentCategoryResult handle(ChangeEquipmentCategoryCommand command) {
        var equipmentCategory = equipmentCategoryRepository
                .findBy(new EquipmentCategoryId(command.getEquipmentCategoryId())).orElseThrow();
        equipmentCategory.changeName(new EquipmentCategoryName(command.getName()));
        equipmentCategoryRepository.update(equipmentCategory);

        return ChangeEquipmentCategoryResult.builder()
                .equipmentCategoryId(equipmentCategory.getEquipmentCategoryId().getValue())
                .name(equipmentCategory.getName().getValue())
                .build();
    }
}
