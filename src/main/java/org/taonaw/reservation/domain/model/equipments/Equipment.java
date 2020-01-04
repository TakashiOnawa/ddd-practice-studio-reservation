package org.taonaw.reservation.domain.model.equipments;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.ReservedEquipmentQuantity;

public class Equipment {
    private final EquipmentId equipmentId;
    private final EquipmentStock equipmentStock;

    public Equipment(
            @NonNull EquipmentId equipmentId,
            @NonNull EquipmentStock equipmentStock) {
        this.equipmentId = equipmentId;
        this.equipmentStock = equipmentStock;
    }

    public EquipmentId equipmentId() {
        return this.equipmentId;
    }

    public boolean isOutOfStock(@NonNull ReservedEquipmentQuantity reservedEquipmentQuantity) {
        return this.equipmentStock.isOutOfStock(reservedEquipmentQuantity);
    }

}
