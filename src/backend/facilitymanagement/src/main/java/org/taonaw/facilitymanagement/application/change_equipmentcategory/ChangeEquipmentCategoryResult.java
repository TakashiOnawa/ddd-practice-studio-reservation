package org.taonaw.facilitymanagement.application.change_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentCategoryResult {
    @NonNull private String equipmentCategoryId;
    @NonNull private String name;
}
