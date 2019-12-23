package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class RentalEquipments {
    private final HashMap<EquipmentId, RentalEquipment> rentalEquipments = new HashMap<>();

    public Collection<RentalEquipment> items() {
        return Collections.unmodifiableCollection(rentalEquipments.values());
    }

    public void add(@NonNull RentalEquipment rentalEquipment) {
        if (rentalEquipments.containsKey(rentalEquipment.getEquipmentId())) {
            RentalEquipment targetRentalEquipment = rentalEquipments.get(rentalEquipment.getEquipmentId());
            targetRentalEquipment = targetRentalEquipment.increase(rentalEquipment.getQuantity());
            rentalEquipments.replace(targetRentalEquipment.getEquipmentId(), targetRentalEquipment);
        } else {
            rentalEquipments.put(rentalEquipment.getEquipmentId(), rentalEquipment);
        }
    }
}
