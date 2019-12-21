package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ReservationId {
    private final String value;

    public ReservationId() {
        this.value = UUID.randomUUID().toString();
    }

    public ReservationId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
