package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.NonNull;

import java.util.Optional;

public interface IEquipmentRepository {
    void add(@NonNull Equipment equipment);
    Optional<Equipment> findBy(@NonNull EquipmentId equipmentId);
}
