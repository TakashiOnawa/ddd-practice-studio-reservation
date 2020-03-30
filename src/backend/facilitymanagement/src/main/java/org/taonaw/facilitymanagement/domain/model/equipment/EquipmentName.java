package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentName {
    private final String value;

    public EquipmentName(@NonNull String value) {
        Assertion.argumentRange(value, 1, 100);
        this.value = value;
    }
}
