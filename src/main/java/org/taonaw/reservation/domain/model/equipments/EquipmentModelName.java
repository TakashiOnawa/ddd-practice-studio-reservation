package org.taonaw.reservation.domain.model.equipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentModelName {
    private final String value;

    public EquipmentModelName(String value) {
        Assertion.argumentRange(value, 1, 64);
        this.value = value;
    }
}
