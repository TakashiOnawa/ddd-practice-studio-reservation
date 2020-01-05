package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import org.taonaw.common.Assertion;

@EqualsAndHashCode
public class NumberOfUsers {
    private final int value;

    public NumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }

    public boolean lessThanOrEqualTo(int otherValue) {
        return this.value <= otherValue;
    }
}
