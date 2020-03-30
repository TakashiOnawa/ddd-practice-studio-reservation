package org.taonaw.facilitymanagement.domain.model.equipmentcategory;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IEquipmentCategoryRepository {
    List<EquipmentCategory> findAll();
    Optional<EquipmentCategory> findBy(@NonNull EquipmentCategoryId equipmentCategoryId);
    void add(@NonNull EquipmentCategory equipmentCategory);
}
