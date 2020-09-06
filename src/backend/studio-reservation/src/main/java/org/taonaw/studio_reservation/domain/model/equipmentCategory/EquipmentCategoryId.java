package org.taonaw.studio_reservation.domain.model.equipmentCategory;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class EquipmentCategoryId {
    private final String value;

    public EquipmentCategoryId(@NonNull String value) {
        this.value = value;
    }

    public static EquipmentCategoryId newId() {
        return new EquipmentCategoryId(new ULID().nextULID());
    }
}
