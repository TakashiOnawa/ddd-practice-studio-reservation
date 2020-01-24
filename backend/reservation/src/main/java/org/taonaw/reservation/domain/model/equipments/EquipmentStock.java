package org.taonaw.reservation.domain.model.equipments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.ReservedEquipmentQuantity;

@AllArgsConstructor
@EqualsAndHashCode
public class EquipmentStock {
    private final int value;

    public boolean isOutOfStock(@NonNull ReservedEquipmentQuantity reservedEquipmentQuantity) {
        return reservedEquipmentQuantity.greaterThan(value);
    }
}
