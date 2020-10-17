package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentStockCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservedUsageEquipments {
    private final Map<EquipmentId, List<UsageEquipment>> items = new HashMap<>();

    public static ReservedUsageEquipments create(@NonNull List<Reservation> reservations, @NonNull Reservation overWriteReservation) {
        var reservedUsageEquipments = new ReservedUsageEquipments();

        for (var reservation : reservations) {
            var usageEquipments = reservation.equals(overWriteReservation) ?
                    overWriteReservation.usageEquipments() :
                    reservation.usageEquipments();

            for (var usageEquipment : usageEquipments.items()) {
                var equipments = reservedUsageEquipments.items
                        .computeIfAbsent(usageEquipment.getEquipmentId(), f -> new ArrayList<>());
                equipments.add(usageEquipment);
            }
        }

        return reservedUsageEquipments;
    }

    public List<EquipmentId> notSatisfyEquipments(Map<EquipmentId, EquipmentStockCount> equipmentStockCounts) {
        var notSatisfyEquipmentIds = new ArrayList<EquipmentId>();

        for (var usageEquipmentsEntry : items.entrySet()) {
            var equipmentStockCount = equipmentStockCounts.get(usageEquipmentsEntry.getKey());

            if (equipmentStockCount == null)
                continue;

            var reservedUsageEquipmentQuantity = usageEquipmentsEntry.getValue().stream()
                    .mapToInt(UsageEquipment::getQuantity)
                    .sum();

            if (reservedUsageEquipmentQuantity > equipmentStockCount.getValue()) {
                notSatisfyEquipmentIds.add(usageEquipmentsEntry.getKey());
            }
        }

        return notSatisfyEquipmentIds;
    }
}
