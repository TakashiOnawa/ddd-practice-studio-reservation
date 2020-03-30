package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    public static MaxNumberOfUsers UNLIMITED = new MaxNumberOfUsers(Integer.MAX_VALUE) {
        @Override
        public boolean isSatisfiedBy(NumberOfUsers numberOfUsers) {
            return true;
        }
    };

    public MaxNumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }

    public boolean isSatisfiedBy(NumberOfUsers numberOfUsers) {
        return numberOfUsers.getValue() <= value;
    }
}
