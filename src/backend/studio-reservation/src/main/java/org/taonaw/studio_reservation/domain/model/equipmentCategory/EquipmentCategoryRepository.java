package org.taonaw.studio_reservation.domain.model.equipmentCategory;

import java.util.Optional;

public interface EquipmentCategoryRepository {
    Optional<EquipmentCategory> findBy(EquipmentCategoryId equipmentCategoryId);
    void add(EquipmentCategory equipmentCategory);
}
