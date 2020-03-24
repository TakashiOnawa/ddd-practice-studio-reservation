package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.equipment.Equipment;
import org.taonaw.reservation.domain.model.equipment.EquipmentId;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;

import java.util.Optional;

@Repository
public class EquipmentRepository implements IEquipmentRepository {
    @Override
    public Optional<Equipment> findBy(@NonNull EquipmentId equipmentId) {
        return Optional.empty();
    }
}
