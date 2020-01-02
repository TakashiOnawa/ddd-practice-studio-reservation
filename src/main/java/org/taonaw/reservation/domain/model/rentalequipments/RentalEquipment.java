package org.taonaw.reservation.domain.model.rentalequipments;

import lombok.NonNull;

public class RentalEquipment {
    private final RentalEquipmentId equipmentId;
    private final RentalEquipmentStock rentalEquipmentStock;

    private RentalEquipment(
            @NonNull RentalEquipmentId equipmentId,
            @NonNull RentalEquipmentStock rentalEquipmentStock) {
        this.equipmentId = equipmentId;
        this.rentalEquipmentStock = rentalEquipmentStock;
    }
}
