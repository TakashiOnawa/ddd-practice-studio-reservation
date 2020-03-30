package org.taonaw.facilitymanagement.domain.model.equipmentcategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentCategoryName {
    private final String value;

    public EquipmentCategoryName(@NonNull String value) {
        Assertion.argumentRange(value, 1, 50);
        this.value = value;
    }
}
