package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class NumberOfUsers {
    private final int value;

    public NumberOfUsers(int value) {
        Assertion.argumentRange(value, 1, 10);
        this.value = value;
    }

    public boolean greatherThan(int value) {
        return this.value > value;
    }

    public boolean greatherThan(NumberOfUsers other) {
        return value > other.value;
    }
}
