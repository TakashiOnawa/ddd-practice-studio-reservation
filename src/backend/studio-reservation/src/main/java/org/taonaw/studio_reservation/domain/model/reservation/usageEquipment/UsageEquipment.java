package org.taonaw.studio_reservation.domain.model.reservation.usageEquipment;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryId;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;

import java.util.Objects;

public class UsageEquipment {
    private EquipmentId equipmentId;
    private EquipmentCategoryId equipmentCategoryId;
    private UsageEquipmentQuantity quantity;

    private UsageEquipment(@NonNull EquipmentId equipmentId, @NonNull EquipmentCategoryId equipmentCategoryId) {
        this.equipmentId = equipmentId;
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public static UsageEquipment create(
            @NonNull EquipmentId equipmentId,
            @NonNull EquipmentCategoryId equipmentCategoryId,
            @NonNull UsageEquipmentQuantity quantity) {

        var instance = new UsageEquipment(equipmentId, equipmentCategoryId);
        instance.quantity = quantity;
        return instance;
    }

    public boolean satisfy(@NonNull EquipmentMaxUsableCount equipmentMaxUsableCount) {
        return false;
    }

    public EquipmentId getEquipmentId() {
        return equipmentId;
    }

    public EquipmentCategoryId getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public UsageEquipmentQuantity getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsageEquipment that = (UsageEquipment) o;
        return equipmentId.equals(that.equipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId);
    }
}
