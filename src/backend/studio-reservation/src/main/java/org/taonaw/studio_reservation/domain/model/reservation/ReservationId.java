package org.taonaw.studio_reservation.domain.model.reservation;

import de.huxhorn.sulky.ulid.ULID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class ReservationId {
    private final String value;

    public ReservationId(String value) {
        Assertion.required(value);
        this.value = value;
    }

    public static ReservationId newId() {
        return new ReservationId(new ULID().nextULID());
    }
}
