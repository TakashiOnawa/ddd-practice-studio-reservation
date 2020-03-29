package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;
import org.taonaw.facilitymanagement.domain.model.equipment.IEquipmentRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentRepository implements IEquipmentRepository {
    private final static List<Equipment> values = new ArrayList<>();

    @Override
    public void add(@NonNull Equipment equipment) {
        values.add(DeepCopy.clone(equipment, Equipment.class));
    }
}
