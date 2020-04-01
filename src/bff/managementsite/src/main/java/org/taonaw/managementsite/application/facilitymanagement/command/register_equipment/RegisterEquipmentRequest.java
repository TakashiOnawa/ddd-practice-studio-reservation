package org.taonaw.managementsite.application.facilitymanagement.command.register_equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor
public class RegisterEquipmentRequest {
    @NonNull
    private String name;
    private int stocks;
    @NonNull private String categoryId;
}
