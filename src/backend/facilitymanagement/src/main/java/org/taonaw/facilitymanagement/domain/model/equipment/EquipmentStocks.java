package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentStocks {
    private final int value;

    public EquipmentStocks(int value) {
        Assertion.argumentRange(value, 0, 99);
        this.value = value;
    }
}