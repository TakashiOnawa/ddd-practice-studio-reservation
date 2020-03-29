package org.taonaw.facilitymanagement.application.register_equipmentcategory;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryName;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;

@Service
@AllArgsConstructor
public class RegisterEquipmentCategoryAppService {
    @Autowired
    private final IEquipmentCategoryRepository equipmentCategoryRepository;

    public RegisterEquipmentCategoryResponse handle(RegisterEquipmentCategoryRequest request) {
        var equipmentCategory = EquipmentCategory.newEquipmentCategory(new EquipmentCategoryName(request.getName()));

        equipmentCategoryRepository.add(equipmentCategory);

        return RegisterEquipmentCategoryResponse.builder()
                .equipmentCategoryId(equipmentCategory.getEquipmentCategoryId().getValue())
                .build();
    }
}
