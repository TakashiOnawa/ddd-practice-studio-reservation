package org.taonaw.reservation.domain.model.equipments;

import lombok.NonNull;

public class Equipment {
    private final EquipmentId equipmentId;
    private final EquipmentTypes equipmentType;
    private EquipmentModelName modelName;
    private EquipmentStockQuantity stockQuantity;

    private Equipment(
            @NonNull EquipmentId equipmentId,
            @NonNull EquipmentTypes equipmentType,
            @NonNull EquipmentModelName modelName) {
        this.equipmentId = equipmentId;
        this.equipmentType = equipmentType;
        this.modelName = modelName;
        this.stockQuantity = new EquipmentStockQuantity(0);
    }

    public Equipment newEquipment(
            @NonNull EquipmentTypes equipmentType,
            @NonNull EquipmentModelName modelName) {
        Equipment equipment = new Equipment(new EquipmentId(), equipmentType, modelName);
        return equipment;
    }

    public void AddStock(@NonNull EquipmentStockQuantity quantity) {
        this.stockQuantity = this.stockQuantity.Add(quantity);
    }

    public static Equipment reconstruct(
            @NonNull EquipmentId equipmentId,
            @NonNull EquipmentTypes equipmentType,
            @NonNull EquipmentModelName modelName,
            @NonNull EquipmentStockQuantity stockQuantity) {
        Equipment equipment = new Equipment(equipmentId, equipmentType, modelName);
        equipment.stockQuantity = stockQuantity;
        return equipment;
    }
}
