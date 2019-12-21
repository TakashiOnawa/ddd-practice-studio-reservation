package org.taonaw.reservation.domain.model.reservations;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.HashMap;

@Getter
public class RentalEquipments {
    private final HashMap<EquipmentId, RentalEquipment> rentalEquipments = new HashMap<>();

    public void add(@NonNull RentalEquipment rentalEquipment) {
        if (rentalEquipments.containsKey(rentalEquipment.getEquipmentId())) {
            var targetRentalEquipment = rentalEquipments.get(rentalEquipment.getEquipmentId());
            targetRentalEquipment = targetRentalEquipment.increase(rentalEquipment.getQuantity());
            rentalEquipments.remove(targetRentalEquipment.getEquipmentId());
            rentalEquipments.put(targetRentalEquipment.getEquipmentId(), targetRentalEquipment);
        } else {
            rentalEquipments.put(rentalEquipment.getEquipmentId(), rentalEquipment);
        }
    }
}
