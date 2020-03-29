package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class EquipmentId {
    private final String value;

    public static EquipmentId newId() {
        return new EquipmentId(UUID.randomUUID().toString());
    }

    public EquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Equipment id is required.");
        this.value = value;
    }
}
