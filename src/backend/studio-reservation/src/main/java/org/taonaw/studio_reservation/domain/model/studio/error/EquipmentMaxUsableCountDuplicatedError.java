package org.taonaw.studio_reservation.domain.model.studio.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.studio.EquipmentMaxUsableCount;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquipmentMaxUsableCountDuplicatedError extends Error {
    private final Set<EquipmentMaxUsableCount> errorEquipmentMaxUsableCounts = new HashSet<>();

    public EquipmentMaxUsableCountDuplicatedError(@NonNull List<EquipmentMaxUsableCount> errorEquipmentMaxUsableCounts) {
        super("機材最大利用数が重複しています。");
        this.errorEquipmentMaxUsableCounts.addAll(errorEquipmentMaxUsableCounts);
    }
}
