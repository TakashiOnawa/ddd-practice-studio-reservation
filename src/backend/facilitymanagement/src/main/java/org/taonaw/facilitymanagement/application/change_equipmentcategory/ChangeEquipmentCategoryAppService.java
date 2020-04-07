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

    public ChangeEquipmentCategoryResponse handle(ChangeEquipmentCategoryRequest request) {
        var equipmentCategory = equipmentCategoryRepository
                .findBy(new EquipmentCategoryId(request.getEquipmentCategoryId())).orElseThrow();
        equipmentCategory.changeName(new EquipmentCategoryName(request.getName()));
        equipmentCategoryRepository.update(equipmentCategory);

        return ChangeEquipmentCategoryResponse.builder()
                .equipmentCategoryId(equipmentCategory.getEquipmentCategoryId().getValue())
                .name(equipmentCategory.getName().getValue())
                .build();
    }
}
