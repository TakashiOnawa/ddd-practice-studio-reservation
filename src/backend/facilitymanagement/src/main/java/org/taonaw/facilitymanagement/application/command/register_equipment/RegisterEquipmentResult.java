package org.taonaw.facilitymanagement.application.command.register_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentResult {
    @NonNull private String equipmentId;

    static RegisterEquipmentResult of(Equipment equipment) {
        return builder()
                .equipmentId(equipment.getEquipmentId().getValue())
                .build();
    }
}
