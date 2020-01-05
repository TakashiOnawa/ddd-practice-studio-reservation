package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    private static final int MIN = 1;
    private static final int MAX = 100;

    public MaxNumberOfUsers(int value) {
        Assertion.argumentRange(value, MIN, MAX);
        this.value = value;
    }

    public static MaxNumberOfUsers Max() {
        return new MaxNumberOfUsers(MAX);
    }

    public boolean isSatisfiedBy(@NonNull NumberOfUsers numberOfUsers) {
        return numberOfUsers.lessThanOrEqualTo(value);
    }
}
