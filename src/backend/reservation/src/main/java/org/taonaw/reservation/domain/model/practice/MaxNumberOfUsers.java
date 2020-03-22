package org.taonaw.reservation.domain.model.practice;

import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    public MaxNumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }

    public boolean isSatisfiedBy(NumberOfUsers numberOfUsers) {
        return numberOfUsers.getValue() <= value;
    }
}
