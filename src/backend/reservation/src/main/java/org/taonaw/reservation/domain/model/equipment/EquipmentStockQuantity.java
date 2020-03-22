package org.taonaw.reservation.domain.model.equipment;

import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class EquipmentStockQuantity {
    private final int value;

    public EquipmentStockQuantity(int value) {
        Assertion.argumentMin(value, 0);
        this.value = value;
    }

    public boolean isOutOfStocks(int quantity) {
        return quantity > value;
    }
}
