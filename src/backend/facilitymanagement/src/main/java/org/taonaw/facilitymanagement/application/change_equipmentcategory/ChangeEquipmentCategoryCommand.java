package org.taonaw.facilitymanagement.application.change_equipmentcategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NegativeOrZero;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentCategoryCommand {
    @NonNull private String equipmentCategoryId;
    @NonNull private String name;
}
