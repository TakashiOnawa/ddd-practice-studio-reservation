package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EquipmentCategoryRepository implements IEquipmentCategoryRepository {
    private final static Map<EquipmentCategoryId, EquipmentCategory> values = new HashMap<>();

    @Override
    public void add(@NonNull EquipmentCategory equipmentCategory) {
        values.put(equipmentCategory.getEquipmentCategoryId(), DeepCopy.clone(equipmentCategory, EquipmentCategory.class));
    }
}
