package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsageEquipments {
    private final List<UsageEquipment> items = new ArrayList<>();

    public UsageEquipments(List<UsageEquipment> usageEquipments) {
        Assertion.required(usageEquipments);
        // TODO: 利用機材のマージ。

        items.addAll(usageEquipments);
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
