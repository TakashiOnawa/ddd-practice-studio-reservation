package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UsageEquipment {
    private final EquipmentId equipmentId;
    private int quantity;

    private UsageEquipment(EquipmentId equipmentId, int quantity) {
        Assertion.required(equipmentId);
        Assertion.argumentRange(quantity, 1, 99);
        this.equipmentId = equipmentId;
        this.quantity = quantity;
    }

    public UsageEquipment add(@NonNull UsageEquipment other) {
        if (this.equipmentId != other.getEquipmentId())
            throw new IllegalArgumentException("異なる機材の数量は追加できません。");

        return new UsageEquipment(this.equipmentId,this.quantity + other.quantity);
    }

    public boolean satisfy(@NonNull EquipmentMaxUsableCount equipmentMaxUsableCount) {
        return false;
    }
}
