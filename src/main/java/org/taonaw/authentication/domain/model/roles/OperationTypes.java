package org.taonaw.authentication.domain.model.roles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OperationTypes {
    Reserve(1, "Reserve"),
    Admin(2, "Admin");

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
