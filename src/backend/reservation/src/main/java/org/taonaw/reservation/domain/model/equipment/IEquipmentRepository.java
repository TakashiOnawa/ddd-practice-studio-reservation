package org.taonaw.reservation.domain.model.equipment;

import lombok.NonNull;

import java.util.Optional;

public interface IEquipmentRepository {
    Optional<Equipment> findBy(@NonNull EquipmentId equipmentId);
}
