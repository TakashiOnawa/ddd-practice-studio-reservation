package org.taonaw.studio_reservation.domain.model.equipment;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryId;

import java.util.Objects;

public class Equipment {
    private EquipmentId id;
    private EquipmentName name;
    private EquipmentCategoryId categoryId;
    private EquipmentStockCount stockCount;

    public static Equipment create(
            @NonNull EquipmentName name,
            @NonNull EquipmentCategoryId categoryId,
            @NonNull EquipmentStockCount stockCount) {

        var instance = new Equipment();
        instance.id = EquipmentId.newId();
        instance.name = name;
        instance.categoryId = categoryId;
        instance.stockCount = stockCount;
        return instance;
    }

    public EquipmentId getId() {
        return id;
    }

    public EquipmentName getName() {
        return name;
    }

    public EquipmentCategoryId getCategoryId() {
        return categoryId;
    }

    public EquipmentStockCount getStockCount() {
        return stockCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return id.equals(equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
