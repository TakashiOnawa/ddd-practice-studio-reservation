package org.taonaw.reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;

import java.util.*;

public class UseEquipments {
    private final Map<EquipmentId, UseEquipment> equipments = new HashMap<>();

    public static UseEquipments of(@NonNull List<EquipmentId> equipmentIds) {
        var result = new UseEquipments();
        result.add(equipmentIds);
        return result;
    }

    Collection<UseEquipment> getUseEquipments() {
        return equipments.values();
    }

    void add(@NonNull EquipmentId equipmentId) {
        if (equipments.containsKey(equipmentId)) {
            var useEquipment = equipments.get(equipmentId);
            useEquipment = useEquipment.incrementQuantity();
            equipments.replace(equipmentId, useEquipment);
        } else {
            var equipmentOfUsage = new UseEquipment(equipmentId, 1);
            equipments.put(equipmentId, equipmentOfUsage);
        }
    }

    void add(@NonNull List<EquipmentId> equipmentIds) {
        equipmentIds.forEach(this::add);
    }
}
