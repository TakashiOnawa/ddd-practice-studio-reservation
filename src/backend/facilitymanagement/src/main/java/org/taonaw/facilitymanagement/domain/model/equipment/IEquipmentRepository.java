package org.taonaw.facilitymanagement.domain.model.equipment;

import lombok.NonNull;

public interface IEquipmentRepository {
    void add(@NonNull Equipment equipment);
}
