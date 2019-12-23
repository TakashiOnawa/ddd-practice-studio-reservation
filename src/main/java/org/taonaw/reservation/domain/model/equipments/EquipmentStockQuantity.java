package org.taonaw.reservation.domain.model.equipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentStockQuantity {
    private final int value;
    public EquipmentStockQuantity(int value) {
        Assertion.argumentRange(value, 0, 100);
        this.value = value;
    }

    public EquipmentStockQuantity add(@NonNull EquipmentStockQuantity quantity) {
        return new EquipmentStockQuantity(this.value + quantity.value);
    }
}
