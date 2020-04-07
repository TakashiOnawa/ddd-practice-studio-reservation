package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryId;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.IEquipmentCategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EquipmentCategoryRepository implements IEquipmentCategoryRepository {
    private final static Map<EquipmentCategoryId, EquipmentCategory> values = new HashMap<>();

    @Override
    public List<EquipmentCategory> findAll() {
        return values.values().stream()
                .map(item -> DeepCopy.clone(item, EquipmentCategory.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EquipmentCategory> findBy(@NonNull EquipmentCategoryId equipmentCategoryId) {
        var equipmentCategory = values.get(equipmentCategoryId);
        if (equipmentCategory == null) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(equipmentCategory, EquipmentCategory.class));
    }

    @Override
    public void add(@NonNull EquipmentCategory equipmentCategory) {
        values.put(equipmentCategory.getEquipmentCategoryId(), DeepCopy.clone(equipmentCategory, EquipmentCategory.class));
    }

    @Override
    public void update(@NonNull EquipmentCategory equipmentCategory) {
        values.replace(equipmentCategory.getEquipmentCategoryId(), DeepCopy.clone(equipmentCategory, EquipmentCategory.class));
    }
}
