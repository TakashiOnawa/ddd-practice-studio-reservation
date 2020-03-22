package org.taonaw.reservation.domain.model.equipment;

import lombok.NonNull;

import java.util.Objects;

public class Equipment {
    private final EquipmentId equipmentId;
    private final EquipmentStockQuantity equipmentStockQuantity;

    public Equipment(@NonNull EquipmentId equipmentId,
                     @NonNull EquipmentStockQuantity equipmentStockQuantity) {
        this.equipmentId = equipmentId;
        this.equipmentStockQuantity = equipmentStockQuantity;
    }

    public EquipmentId getEquipmentId() {
        return equipmentId;
    }

    public EquipmentStockQuantity getEquipmentStockQuantity() {
        return equipmentStockQuantity;
    }

    public boolean isOutOfStocks(int reservedEquipmentQuantity) {
        return equipmentStockQuantity.isOutOfStocks(reservedEquipmentQuantity);
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
