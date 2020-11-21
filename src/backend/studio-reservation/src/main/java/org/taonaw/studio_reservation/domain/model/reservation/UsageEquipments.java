package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class UsageEquipments {
    private final List<UsageEquipment> items;

    public UsageEquipments(List<UsageEquipment> items) {
        Assertion.required(items);

        // 同じ機材の数量をマージする。
        Map<EquipmentId, UsageEquipment> map = new HashMap<>();
        for (var item : items) {
            var mappedUsageEquipment = map.get(item.getEquipmentId());
            if (mappedUsageEquipment == null) {
                map.put(item.getEquipmentId(), item);
            } else {
                map.put(item.getEquipmentId(), item.add(item));
            }
        }
        this.items = new ArrayList<>(map.values());
    }

    public static UsageEquipments empty() {
        return new UsageEquipments(new ArrayList<>());
    }

    public List<UsageEquipment> items() {
        return new ArrayList<>(items);
    }

    public List<EquipmentId> getUsageEquipmentIds() {
        return items.stream().map(UsageEquipment::getEquipmentId).collect(Collectors.toList());
    }

    public List<EquipmentId> notSatisfyEquipments(@NonNull Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts) {
        var notSatisfyEquipmentIds = new ArrayList<EquipmentId>();

        for (var item : items) {
            var equipmentMaxUsableCount = equipmentMaxUsableCounts.get(item.getEquipmentId());

            if (equipmentMaxUsableCount == null)
                continue;

            if (!item.satisfy(equipmentMaxUsableCount)) {
                notSatisfyEquipmentIds.add(item.getEquipmentId());
            }
        }

        return notSatisfyEquipmentIds;
    }
}
