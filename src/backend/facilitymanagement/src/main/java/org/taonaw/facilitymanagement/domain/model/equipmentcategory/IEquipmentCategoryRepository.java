package org.taonaw.facilitymanagement.domain.model.equipmentcategory;

import lombok.NonNull;

public interface IEquipmentCategoryRepository {
    void add(@NonNull EquipmentCategory equipmentCategory);
}
