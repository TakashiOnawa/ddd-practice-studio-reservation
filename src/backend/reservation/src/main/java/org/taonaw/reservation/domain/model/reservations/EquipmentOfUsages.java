package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipments.EquipmentId;

import java.util.*;

public class EquipmentOfUsages {
    private final Map<EquipmentId, EquipmentOfUsage> values = new HashMap<>();

    public static EquipmentOfUsages of(@NonNull List<EquipmentId> equipmentIds) {
        var result = new EquipmentOfUsages();
        result.add(equipmentIds);
        return result;
    }

    Collection<EquipmentOfUsage> values() {
        return Collections.unmodifiableCollection(this.values.values());
    }

    void add(@NonNull EquipmentId equipmentId) {
        if (this.values.containsKey(equipmentId)) {
            var equipmentOfUsage = this.values.get(equipmentId);
            equipmentOfUsage = equipmentOfUsage.addQuantity(1);
            this.values.replace(equipmentId, equipmentOfUsage);
        } else {
            var equipmentOfUsage = new EquipmentOfUsage(equipmentId, 1);
            this.values.put(equipmentId, equipmentOfUsage);
        }
    }

    void add(@NonNull List<EquipmentId> equipmentIds) {
        equipmentIds.forEach(this::add);
    }
}
