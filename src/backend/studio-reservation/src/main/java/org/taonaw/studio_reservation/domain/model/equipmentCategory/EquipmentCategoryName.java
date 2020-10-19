package org.taonaw.studio_reservation.domain.model.equipmentCategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentCategoryName {
    private final String value;

    public EquipmentCategoryName(String value) {
        Assertion.argumentRange(value, 1, 50);
        this.value = value;
    }
}
