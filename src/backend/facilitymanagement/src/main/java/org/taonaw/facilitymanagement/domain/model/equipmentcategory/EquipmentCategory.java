package org.taonaw.facilitymanagement.domain.model.equipmentcategory;

import lombok.NonNull;

import java.util.Objects;

public class EquipmentCategory {
    private EquipmentCategoryId equipmentCategoryId;
    private EquipmentCategoryName name;

    private EquipmentCategory() { }

    public static EquipmentCategory newEquipmentCategory(@NonNull EquipmentCategoryName name) {
        var equipmentCategory = new EquipmentCategory();
        equipmentCategory.equipmentCategoryId = EquipmentCategoryId.newId();
        equipmentCategory.name = name;
        return equipmentCategory;
    }

    public static EquipmentCategory reconstruct(@NonNull EquipmentCategoryId equipmentCategoryId,
                                                @NonNull EquipmentCategoryName name) {
        var equipmentCategory = new EquipmentCategory();
        equipmentCategory.equipmentCategoryId = equipmentCategoryId;
        equipmentCategory.name = name;
        return equipmentCategory;
    }

    public EquipmentCategoryId getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public EquipmentCategoryName getName() {
        return name;
    }

    public void changeName(EquipmentCategoryName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentCategory that = (EquipmentCategory) o;
        return equipmentCategoryId.equals(that.equipmentCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentCategoryId);
    }
}
