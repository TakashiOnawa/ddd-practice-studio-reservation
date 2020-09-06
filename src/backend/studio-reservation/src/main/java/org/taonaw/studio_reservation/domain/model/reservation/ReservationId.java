package org.taonaw.studio_reservation.domain.model.reservation;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class ReservationId {
    private final String value;

    public ReservationId(@NonNull String value) {
        this.value = value;
    }

    public static ReservationId newId() {
        return new ReservationId(new ULID().nextULID());
    }
}
