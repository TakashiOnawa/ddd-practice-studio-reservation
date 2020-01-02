package org.taonaw.reservation.domain.model.rentalequipments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RentalEquipmentStock {
    private final int value;

    public boolean greaterThan(@NonNull RentalEquipmentStock other) {
        return this.greaterThan(other.value);
    }

    public boolean greaterThan(int otherValue) {
        return this.value > otherValue;
    }
}
