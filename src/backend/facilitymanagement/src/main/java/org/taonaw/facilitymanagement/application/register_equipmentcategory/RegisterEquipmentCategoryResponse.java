package org.taonaw.facilitymanagement.application.register_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCategoryResponse {
    @NonNull private String equipmentCategoryId;
}
