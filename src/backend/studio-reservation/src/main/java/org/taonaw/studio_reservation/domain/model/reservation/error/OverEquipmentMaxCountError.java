package org.taonaw.studio_reservation.domain.model.reservation.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class OverEquipmentMaxCountError extends Error {
    private final List<EquipmentId> errorEquipmentIds;

    public OverEquipmentMaxCountError(@NonNull List<EquipmentId> errorEquipmentIds) {
        super("機材の最大利用可能数を超えています。");
        this.errorEquipmentIds = new ArrayList<>(errorEquipmentIds);
    }

    public List<EquipmentId> getErrorEquipmentIds() {
        return new ArrayList<>(errorEquipmentIds);
    }
}
