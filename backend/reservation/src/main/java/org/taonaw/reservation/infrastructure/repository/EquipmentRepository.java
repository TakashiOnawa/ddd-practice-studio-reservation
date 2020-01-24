package org.taonaw.reservation.infrastructure.repository;

import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.equipments.Equipment;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;

import java.util.List;

@Repository
public class EquipmentRepository implements IEquipmentRepository {

    public List<Equipment> findAll() {
        throw new IllegalCallerException();
    }
}
