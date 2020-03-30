package org.taonaw.facilitymanagement.query.equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class EquipmentDto {
    @NonNull private String equipmentId;
    @NonNull private String name;
    private int stocks;
    @NonNull private String categoryId;
    @NonNull private String categoryName;
}
