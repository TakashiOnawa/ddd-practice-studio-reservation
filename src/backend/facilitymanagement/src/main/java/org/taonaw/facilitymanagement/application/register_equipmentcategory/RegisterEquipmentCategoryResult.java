package org.taonaw.facilitymanagement.application.register_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCategoryResult {
    @NonNull private String equipmentCategoryId;

    static RegisterEquipmentCategoryResult of(EquipmentCategory equipmentCategory) {
        return builder()
                .equipmentCategoryId(equipmentCategory.getEquipmentCategoryId().getValue())
                .build();
    }
}
