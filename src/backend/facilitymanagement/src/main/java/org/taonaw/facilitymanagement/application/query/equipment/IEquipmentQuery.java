package org.taonaw.facilitymanagement.application.query.equipment;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface IEquipmentQuery {
    List<EquipmentDto> getAll();
    Optional<EquipmentDto> getByEquipmentId(@NonNull String equipmentId);
}
