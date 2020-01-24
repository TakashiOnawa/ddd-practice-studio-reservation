package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class ReservedEquipmentQuantity {
    private final int value;

    public ReservedEquipmentQuantity(int value) {
        Assertion.argumentMin(value, 0);
        this.value = value;
    }

    public static ReservedEquipmentQuantity empty() {
        return new ReservedEquipmentQuantity(0);
    }

    public ReservedEquipmentQuantity add(int quantity) {
        return new ReservedEquipmentQuantity(this.value + quantity);
    }

    public boolean greaterThan(int quantity) {
        return this.value > quantity;
    }
}
