package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentId;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EquipmentRepository implements IEquipmentRepository {
    private final static Map<EquipmentId, Equipment> values = new HashMap<>();

    @Override
    public List<Equipment> findAll() {
        return values.values().stream()
                .map(item -> DeepCopy.clone(item, Equipment.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Equipment> findBy(@NonNull EquipmentId equipmentId) {
        var equipment = values.get(equipmentId);
        if (equipment == null) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(equipment, Equipment.class));
    }

    @Override
    public void add(@NonNull Equipment equipment) {
        values.put(equipment.getEquipmentId(), DeepCopy.clone(equipment, Equipment.class));
    }
}
