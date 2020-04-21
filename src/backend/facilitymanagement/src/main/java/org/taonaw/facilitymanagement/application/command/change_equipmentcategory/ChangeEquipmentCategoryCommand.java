package org.taonaw.facilitymanagement.application.command.change_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentCategoryCommand {
    @NonNull private String equipmentCategoryId;
    @NonNull private String name;
}
