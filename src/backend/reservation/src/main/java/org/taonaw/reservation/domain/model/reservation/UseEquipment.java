package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UseEquipment {
    private final EquipmentId equipmentId;
    private final int quantity;

    public UseEquipment(@NonNull EquipmentId equipmentId, int quantity) {
        Assertion.argumentMin(quantity, 0);
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public UseEquipment addQuantity(int addQuantity) {
        Assertion.argumentMin(quantity, 1);
        return new UseEquipment(equipmentId, quantity + addQuantity);
    }
}
