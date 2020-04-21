package org.taonaw.facilitymanagement.application.command.register_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentCategoryCommand {
    @NonNull private String name;
}
