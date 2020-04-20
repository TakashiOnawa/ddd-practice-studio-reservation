package org.taonaw.facilitymanagement.application.change_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;

@Getter
@Builder
@AllArgsConstructor
public class ChangeEquipmentResult {
    @NonNull private String equipmentId;
    @NonNull private String name;
    private int stocks;
    @NonNull private String categoryId;

    static ChangeEquipmentResult of(Equipment equipment) {
        return builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .name(equipment.getName().getValue())
                .stocks(equipment.getStocks().getValue())
                .categoryId(equipment.getCategoryId().getValue())
                .build();
    }
}
