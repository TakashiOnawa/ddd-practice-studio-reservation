package org.taonaw.managementsite.application.facilitymanagement.command.register_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentResponse {
    @NonNull
    private String equipmentId;
}
