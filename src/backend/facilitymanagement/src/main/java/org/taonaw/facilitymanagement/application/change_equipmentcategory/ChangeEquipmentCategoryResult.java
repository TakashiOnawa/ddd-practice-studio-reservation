package org.taonaw.facilitymanagement.application.change_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentCategoryResult {
    @NonNull private String equipmentCategoryId;
    @NonNull private String name;

    static ChangeEquipmentCategoryResult of(EquipmentCategory equipmentCategory) {
        return builder()
                .equipmentCategoryId(equipmentCategory.getEquipmentCategoryId().getValue())
                .name(equipmentCategory.getName().getValue())
                .build();
    }
}
