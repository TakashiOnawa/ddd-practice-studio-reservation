package org.taonaw.reservation.domain.model.equipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.common.domain.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentId {

    private final String value;

    public EquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
