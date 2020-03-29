package org.taonaw.facilitymanagement.domain.model.equipmentcategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class EquipmentCategoryId {
    private final String value;

    public static EquipmentCategoryId newId() {
        return new EquipmentCategoryId(UUID.randomUUID().toString());
    }

    public EquipmentCategoryId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Equipment category id is required.");
        this.value = value;
    }
}
