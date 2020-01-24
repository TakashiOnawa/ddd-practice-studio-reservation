package org.taonaw.identityaccess.domain.model.roles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OperationTypes {
    Reserve(1, "Reserve"),
    EquipmentManagement(2, "EquipmentManagement"),
    StudioManagement(3, "StudioManagement"),
    PriceSetting(4, "PriceSetting"),
    Payment(5, "Payment");

    private final int id;
    private final String name;

    public static OperationTypes of(int id) {
        for (var operationType : values()) {
            if (operationType.id == id) {
                return operationType;
            }
        }
        throw new IllegalArgumentException();
    }
}
