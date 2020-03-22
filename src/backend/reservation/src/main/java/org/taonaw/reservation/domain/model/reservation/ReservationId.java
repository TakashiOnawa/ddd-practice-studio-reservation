package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ReservationId {
    private final String value;

    public static ReservationId newId() {
        return new ReservationId(UUID.randomUUID().toString());
    }

    public ReservationId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Reservation id is required.");
        this.value = value;
    }
}
