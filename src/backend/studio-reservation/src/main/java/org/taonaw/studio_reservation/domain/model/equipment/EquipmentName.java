package org.taonaw.studio_reservation.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentName {
    private final String value;

    public EquipmentName(String value) {
        Assertion.argumentRange(value, 1, 50);
        this.value = value;
    }
}
