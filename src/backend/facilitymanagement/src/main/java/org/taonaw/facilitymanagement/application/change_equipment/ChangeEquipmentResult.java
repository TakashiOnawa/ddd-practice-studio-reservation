package org.taonaw.facilitymanagement.application.change_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentResult {
    @NonNull private String equipmentId;
    @NonNull private String name;
    private int stocks;
    @NonNull private String categoryId;
}
