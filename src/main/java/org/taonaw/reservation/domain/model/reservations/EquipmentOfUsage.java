package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;
import org.taonaw.reservation.domain.model.rentalequipments.RentalEquipmentId;

@Getter
@EqualsAndHashCode
public class EquipmentOfUsage {
    private final RentalEquipmentId rentalEquipmentId;
    private final int quantity;

    public EquipmentOfUsage(@NonNull RentalEquipmentId rentalEquipmentId, int quantity) {
        Assertion.argumentMin(quantity, 1);
        this.rentalEquipmentId = rentalEquipmentId;
        this.quantity = quantity;
    }

    public EquipmentOfUsage increase(int quantity) {
        return new EquipmentOfUsage(this.rentalEquipmentId, this.quantity + quantity);
    }
}
