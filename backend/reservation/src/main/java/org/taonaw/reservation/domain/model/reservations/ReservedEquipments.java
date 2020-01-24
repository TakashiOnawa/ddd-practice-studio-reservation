package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.Equipment;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ReservedEquipments {
    private final Map<EquipmentId, Collection<EquipmentOfUsage>> reservedEquipmentMap = new HashMap<>();

    private ReservedEquipments() { }

    static ReservedEquipments Create(@NonNull Collection<Reservation> reservations) {
        var reservedEquipments = new ReservedEquipments();
        for (var reservation : reservations) {
            reservedEquipments.addAll(reservation.equipmentOfUsages());
        }
        return reservedEquipments;
    }

    private void addAll(Collection<EquipmentOfUsage> equipmentOfUsages) {
        for (var equipment : equipmentOfUsages) {
            var reservedEquipments = reservedEquipmentMap.computeIfAbsent(equipment.getEquipmentId(), s -> new ArrayList<>());
            reservedEquipments.add(equipment);
        }
    }

    public boolean isOutOfStock(Collection<Equipment> equipments) {
        for (var reservedEquipmentId : reservedEquipmentMap.keySet()) {
            var reservedQuantity = getReservedEquipmentQuantity(reservedEquipmentId);
            var equipment = equipments.stream().filter(s -> s.equipmentId().equals(reservedEquipmentId)).findFirst();
            equipment.orElseThrow();
            if (equipment.get().isOutOfStock(reservedQuantity)) {
                return true;
            }
        }
        return false;
    }

    private ReservedEquipmentQuantity getReservedEquipmentQuantity(EquipmentId equipmentId) {
        var reservedEquipmentQuantity = ReservedEquipmentQuantity.empty();
        var reservedEquipments = reservedEquipmentMap.get(equipmentId);
        if (reservedEquipments == null) {
            return reservedEquipmentQuantity;
        }
        for (var reservedEquipment : reservedEquipments) {
            reservedEquipmentQuantity = reservedEquipmentQuantity.add(reservedEquipment.getQuantity());
        }
        return reservedEquipmentQuantity;
    }
}
