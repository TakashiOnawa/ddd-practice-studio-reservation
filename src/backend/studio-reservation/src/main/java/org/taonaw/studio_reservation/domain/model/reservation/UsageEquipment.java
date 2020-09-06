package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UsageEquipment {
    private final EquipmentId equipmentId;
    private final int quantity;

    public UsageEquipment(@NonNull EquipmentId equipmentId, int quantity) {
        Assertion.argumentMin(quantity, 1);
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }
}
