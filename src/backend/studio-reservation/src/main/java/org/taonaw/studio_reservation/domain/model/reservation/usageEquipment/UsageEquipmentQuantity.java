package org.taonaw.studio_reservation.domain.model.reservation.usageEquipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UsageEquipmentQuantity {
    private final int value;

    public UsageEquipmentQuantity(int value) {
        Assertion.argumentRange(value, 1, 99);
        this.value = value;
    }
}
