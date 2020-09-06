package org.taonaw.studio_reservation.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.equipmentCategory.EquipmentCategoryId;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentMaxUsableCount {
    private final EquipmentCategoryId equipmentCategoryId;
    private final int maxUsableCount;

    public EquipmentMaxUsableCount(@NonNull EquipmentCategoryId equipmentCategoryId, int maxUsableCount) {
        Assertion.argumentRange(maxUsableCount, 1, 99);
        this.equipmentCategoryId = equipmentCategoryId;
        this.maxUsableCount = maxUsableCount;
    }
}
