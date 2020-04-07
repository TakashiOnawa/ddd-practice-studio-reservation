package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IEquipmentRepository {
    List<Equipment> findAll();
    Optional<Equipment> findBy(@NonNull EquipmentId equipmentId);
    void add(@NonNull Equipment equipment);
    void update(@NonNull Equipment equipment);
}
