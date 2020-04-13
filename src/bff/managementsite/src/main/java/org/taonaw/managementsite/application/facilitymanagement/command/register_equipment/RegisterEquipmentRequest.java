package org.taonaw.managementsite.application.facilitymanagement.command.register_equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterEquipmentRequest {
    private String name;
    private int stocks;
    private String categoryId;
}
