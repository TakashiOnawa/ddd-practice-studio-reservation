package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

@Getter
@EqualsAndHashCode
public class RentalEquipment {
    private final EquipmentId equipmentId;
    private final int quantity;

    public RentalEquipment(@NonNull EquipmentId equipmentId, int quantity) {
        Assertion.argumentMin(quantity, 1);
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public RentalEquipment increase(int quantity) {
        return new RentalEquipment(this.equipmentId, this.quantity + quantity);
    }
}
