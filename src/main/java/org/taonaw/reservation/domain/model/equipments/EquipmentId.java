package org.taonaw.reservation.domain.model.equipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class EquipmentId {
    private final String value;

    public EquipmentId() {
        this.value = UUID.randomUUID().toString();
    }

    public EquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
