package org.taonaw.reservation.domain.model.equipment;

import lombok.NonNull;

import java.util.Objects;

public class Equipment {
    private final EquipmentId equipmentId;
    private final EquipmentStocks equipmentStocks;

    public Equipment(@NonNull EquipmentId equipmentId,
                     @NonNull EquipmentStocks equipmentStocks) {
        this.equipmentId = equipmentId;
        this.equipmentStocks = equipmentStocks;
    }

    public EquipmentId getEquipmentId() {
        return equipmentId;
    }

    public EquipmentStocks getEquipmentStocks() {
        return equipmentStocks;
    }

    public boolean isOutOfStocks(int reservedEquipmentQuantity) {
        return equipmentStocks.isOutOfStocks(reservedEquipmentQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return equipmentId.equals(equipment.equipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId);
    }
}
