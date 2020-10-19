package org.taonaw.studio_reservation.domain.model.equipmentCategory;

import lombok.NonNull;

import java.util.Objects;

public class EquipmentCategory {
    private EquipmentCategoryId id;
    private EquipmentCategoryName name;

    private EquipmentCategory(@NonNull EquipmentCategoryId id) {
        this.id = id;
    }

    public static EquipmentCategory create(@NonNull EquipmentCategoryName name) {
        var instance = new EquipmentCategory(EquipmentCategoryId.newId());
        instance.name = name;
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentCategory that = (EquipmentCategory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
