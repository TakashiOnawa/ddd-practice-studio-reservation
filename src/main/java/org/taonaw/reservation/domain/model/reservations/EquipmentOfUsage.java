package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.common.domain.Assertion;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

@EqualsAndHashCode
public class EquipmentOfUsage {
    private final EquipmentId equipmentId;
    private final int quantity;

    public EquipmentOfUsage(@NonNull EquipmentId equipmentId, int quantity) {
        Assertion.argumentMin(quantity, 1);
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public EquipmentOfUsage addQuantity(int quantity) {
        return new EquipmentOfUsage(this.equipmentId, this.quantity + quantity);
    }

    public EquipmentId getEquipmentId() {
        return this.equipmentId;
    }

    int getQuantity() {
        return this.quantity;
    }
}
