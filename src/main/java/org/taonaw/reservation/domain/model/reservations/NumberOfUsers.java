package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class NumberOfUsers {
    private final int value;

    public NumberOfUsers(int value) {
        Assertion.argumentRange(value, 1, 10);
        this.value = value;
    }

    public boolean greaterThan(@NonNull NumberOfUsers other) {
        return this.greaterThan(other.value);
    }

    public boolean greaterThan(int otherValue) {
        return this.value > otherValue;
    }
}
