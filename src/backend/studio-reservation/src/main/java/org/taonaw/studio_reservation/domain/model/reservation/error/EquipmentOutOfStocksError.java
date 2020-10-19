package org.taonaw.studio_reservation.domain.model.reservation.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class EquipmentOutOfStocksError extends Error {
    private final List<EquipmentId> errorEquipmentIds;

    public EquipmentOutOfStocksError(@NonNull List<EquipmentId> errorEquipmentIds) {
        super("利用機材の在庫に余りがありません。");
        this.errorEquipmentIds = new ArrayList<>(errorEquipmentIds);
    }

    public List<EquipmentId> getErrorEquipmentIds() {
        return new ArrayList<>(errorEquipmentIds);
    }
}
