package org.taonaw.reservation.domain.model.equipment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
@AllArgsConstructor
public class EquipmentStocks {
    private final int value;

    public boolean isOutOfStocks(int quantity) {
        return quantity > value;
    }
}
