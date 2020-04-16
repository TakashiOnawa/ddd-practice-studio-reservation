package org.taonaw.reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        var useEquipment = equipments.getOrDefault(equipmentId, new UseEquipment(equipmentId, 0));
        equipments.put(equipmentId, useEquipment.incrementQuantity());
    }

    void add(@NonNull List<EquipmentId> equipmentIds) {
        equipmentIds.forEach(this::add);
    }
}
