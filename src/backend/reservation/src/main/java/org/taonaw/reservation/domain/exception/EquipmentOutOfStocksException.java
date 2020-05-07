package org.taonaw.reservation.domain.exception;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentOutOfStocksException extends DomainException {
    private final List<String> equipmentIds;

    public EquipmentOutOfStocksException(@NonNull List<EquipmentId> equipmentIds) {
        super("予約機材の在庫が余っていません。");
        this.equipmentIds = equipmentIds.stream().map(EquipmentId::getValue).collect(Collectors.toList());
    }

    public List<String> getEquipmentIds() {
        return equipmentIds.stream().collect(Collectors.toUnmodifiableList());
    }
}
