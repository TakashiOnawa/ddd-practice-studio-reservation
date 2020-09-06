package org.taonaw.studio_reservation.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentStockCount {
    private final int value;

    public EquipmentStockCount(int value) {
        Assertion.argumentRange(value, 1, 99);
        this.value = value;
    }
}
