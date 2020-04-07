package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;

import java.util.Objects;

public class Equipment {
    private EquipmentId equipmentId;
    private EquipmentName name;
    private EquipmentCategoryId categoryId;
    private EquipmentStocks stocks;

    private Equipment() { }

    public static Equipment newEquipment(@NonNull EquipmentName equipmentName,
                                         @NonNull EquipmentCategoryId equipmentCategoryId,
                                         @NonNull EquipmentStocks equipmentStocks) {
        var equipment = new Equipment();
        equipment.equipmentId = EquipmentId.newId();
        equipment.name = equipmentName;
        equipment.categoryId = equipmentCategoryId;
        equipment.stocks = equipmentStocks;
        return equipment;
    }

    public static Equipment reconstruct(@NonNull EquipmentId equipmentId,
                                        @NonNull EquipmentName equipmentName,
                                        @NonNull EquipmentCategoryId equipmentCategoryId,
                                        @NonNull EquipmentStocks equipmentStocks) {
        var equipment = new Equipment();
        equipment.equipmentId = equipmentId;
        equipment.name = equipmentName;
        equipment.categoryId = equipmentCategoryId;
        equipment.stocks = equipmentStocks;
        return equipment;
    }

    public EquipmentId getEquipmentId() {
        return equipmentId;
    }

    public EquipmentName getName() {
        return name;
    }

    public EquipmentCategoryId getCategoryId() {
        return categoryId;
    }

    public EquipmentStocks getStocks() {
        return stocks;
    }

    public void changeName(@NonNull EquipmentName name) {
        this.name = name;
    }

    public void changeStocks(@NonNull EquipmentStocks stocks) {
        this.stocks = stocks;
    }

    public void changeEquipmentCategory(@NonNull EquipmentCategoryId categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return equipmentId.equals(equipment.equipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId);
    }
}
