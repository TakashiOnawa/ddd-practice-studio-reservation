package org.taonaw.reservation.domain.model.rentalequipments;

import java.util.List;

public interface IRentalEquipmentRepository {
    List<RentalEquipment> findAll();
}
