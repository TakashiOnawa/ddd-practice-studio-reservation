package org.taonaw.managementsite.application.facilitymanagement.command.register_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCategoryRequest {
    @NonNull
    private String name;
}
