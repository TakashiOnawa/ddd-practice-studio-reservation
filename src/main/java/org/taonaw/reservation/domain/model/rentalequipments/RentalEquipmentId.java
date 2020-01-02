package org.taonaw.reservation.domain.model.rentalequipments;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class RentalEquipmentId {
    private final String value;

    public RentalEquipmentId() {
        this.value = UUID.randomUUID().toString();
    }

    public RentalEquipmentId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
