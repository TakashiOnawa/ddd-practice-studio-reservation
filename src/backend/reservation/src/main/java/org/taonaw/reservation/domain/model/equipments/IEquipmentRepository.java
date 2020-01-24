package org.taonaw.reservation.domain.model.equipments;

import java.util.List;

public interface IEquipmentRepository {
    List<Equipment> findAll();
}
