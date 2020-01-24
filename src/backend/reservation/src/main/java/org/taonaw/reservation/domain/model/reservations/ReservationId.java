package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ReservationId {

    private final String value;

    ReservationId() {
        this(UUID.randomUUID().toString());
    }

    public ReservationId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Reservation id is required.");
        this.value = value;
    }
}
