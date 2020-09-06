package org.taonaw.studio_reservation.domain.model.equipment;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class EquipmentId {
    private final String value;

    public EquipmentId(@NonNull String value) {
        this.value = value;
    }

    public static EquipmentId newId() {
        return new EquipmentId(new ULID().nextULID());
    }
}
