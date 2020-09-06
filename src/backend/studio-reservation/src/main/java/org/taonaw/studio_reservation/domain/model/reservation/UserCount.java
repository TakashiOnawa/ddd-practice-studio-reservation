package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UserCount {
    private final int value;

    public UserCount(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }
}
