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
    private final List<UsageEquipment> items = new ArrayList<>();

    public UsageEquipments(List<UsageEquipment> usageEquipments) {
        Assertion.required(usageEquipments);

        // 同じ機材の数量をマージする。
        Map<EquipmentId, UsageEquipment> map = new HashMap<>();
        for (var usageEquipment : usageEquipments) {
            var mappedUsageEquipment = map.get(usageEquipment.getEquipmentId());
            if (mappedUsageEquipment == null) {
                map.put(usageEquipment.getEquipmentId(), usageEquipment);
            } else {
                map.put(usageEquipment.getEquipmentId(), usageEquipment.add(usageEquipment));
            }
        }
        items.addAll(map.values());
    }

    public List<UsageEquipment> items() {
        return new ArrayList<>(items);
    }

    public List<EquipmentId> getUsageEquipmentIds() {
        return items.stream().map(UsageEquipment::getEquipmentId).collect(Collectors.toList());
    }

    public List<EquipmentId> notSatisfyEquipments(@NonNull Map<EquipmentId, EquipmentMaxUsableCount> equipmentMaxUsableCounts) {
        var notSatisfyEquipmentIds = new ArrayList<EquipmentId>();

        for (var usageEquipment : items) {
            var equipmentMaxUsableCount = equipmentMaxUsableCounts.get(usageEquipment.getEquipmentId());

            if (equipmentMaxUsableCount == null)
                continue;

            if (!usageEquipment.satisfy(equipmentMaxUsableCount)) {
                notSatisfyEquipmentIds.add(usageEquipment.getEquipmentId());
            }
        }

        return notSatisfyEquipmentIds;
    }
}
