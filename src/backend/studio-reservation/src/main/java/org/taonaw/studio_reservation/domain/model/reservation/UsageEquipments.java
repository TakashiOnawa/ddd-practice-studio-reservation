package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.reservation.usageEquipment.UsageEquipment;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;

import java.util.ArrayList;
import java.util.List;

public class UsageEquipments {
    private final List<UsageEquipment> items = new ArrayList<>();

    public UsageEquipments(@NonNull List<UsageEquipment> usageEquipments) {
        // TODO: 利用機材のマージ。

        items.addAll(usageEquipments);
    }

    public List<EquipmentId> notSatisfyEquipments(@NonNull List<EquipmentMaxUsableCount> equipmentMaxUsableCounts) {
        var notSatisfyEquipmentIds = new ArrayList<EquipmentId>();

        for (var usageEquipment : items) {
            var equipmentMaxCountOption = equipmentMaxUsableCounts.stream()
                    .filter(item -> item.getEquipmentCategoryId().equals(usageEquipment.getEquipmentCategoryId()))
                    .findFirst();

            if (equipmentMaxCountOption.isEmpty())
                continue;

            if (!usageEquipment.satisfy(equipmentMaxCountOption.get())) {
                notSatisfyEquipmentIds.add(usageEquipment.getEquipmentId());
            }
        }

        return notSatisfyEquipmentIds;
    }
}
