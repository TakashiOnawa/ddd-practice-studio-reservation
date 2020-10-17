package org.taonaw.studio_reservation.domain.model.equipment;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class EquipmentId {
    private final String value;

    public EquipmentId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static EquipmentId newId() {
        return new EquipmentId(new ULID().nextULID());
    }
}
