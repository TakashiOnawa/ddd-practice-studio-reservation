package org.taonaw.reservation.domain.model.equipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentId {

    private final String value;

    public EquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Equipment id is required.");
        this.value = value;
    }
}
