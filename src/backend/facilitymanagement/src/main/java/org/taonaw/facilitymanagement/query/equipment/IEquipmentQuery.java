package org.taonaw.facilitymanagement.query.equipment;

import lombok.NonNull;

import java.util.Optional;

public interface IEquipmentQuery {
    Optional<EquipmentDto> getByEquipmentId(@NonNull String equipmentId);
}
