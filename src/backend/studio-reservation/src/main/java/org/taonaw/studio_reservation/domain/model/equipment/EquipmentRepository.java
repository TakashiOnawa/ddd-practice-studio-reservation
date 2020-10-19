package org.taonaw.studio_reservation.domain.model.equipment;

import java.util.Optional;

public interface EquipmentRepository {
    Optional<Equipment> findBy(EquipmentId equipmentId);
    void add(Equipment equipment);
}
