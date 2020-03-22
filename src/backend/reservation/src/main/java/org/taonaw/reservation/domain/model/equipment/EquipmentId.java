package org.taonaw.reservation.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class EquipmentId {
    private final String value;

    public EquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Equipment id is required.");
        this.value = value;
    }
}
