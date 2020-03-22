package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
class ReservedEquipments {
    private final Map<EquipmentId, Collection<UseEquipment>> reservedEquipments = new HashMap<>();

    private ReservedEquipments() { }

    public static ReservedEquipments from(@NonNull Collection<Reservation> reservations) {
        var ret = new ReservedEquipments();
        for (var reservation : reservations) {
            for (var useEquipment : reservation.getUseEquipments()) {
                var equipments = ret.reservedEquipments
                        .computeIfAbsent(useEquipment.getEquipmentId(), k -> new ArrayList<>());
                equipments.add(useEquipment);
            }
        }
        return ret;
    }

    public int getQuantity(@NonNull EquipmentId equipmentId) {
        var quantity = 0;
        var useEquipments = reservedEquipments.get(equipmentId);
        if (useEquipments == null) {
            return quantity;
        }

        for (var useEquipment : useEquipments) {
            quantity += useEquipment.getQuantity();
        }
        return quantity;
    }
}
