package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentCategoryRepository implements IEquipmentCategoryRepository {
    private final static List<EquipmentCategory> values = new ArrayList<>();

    @Override
    public void add(@NonNull EquipmentCategory equipmentCategory) {
        values.add(DeepCopy.clone(equipmentCategory, EquipmentCategory.class));
    }
}
