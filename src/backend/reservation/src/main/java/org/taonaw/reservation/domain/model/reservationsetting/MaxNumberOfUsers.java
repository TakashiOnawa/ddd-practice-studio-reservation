package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
@AllArgsConstructor
public class MaxNumberOfUsers {
    private final int value;

    public static MaxNumberOfUsers UNLIMITED = new MaxNumberOfUsers(Integer.MAX_VALUE) {
        @Override
        public boolean isSatisfiedBy(NumberOfUsers numberOfUsers) {
            return true;
        }
    };

    public boolean isSatisfiedBy(NumberOfUsers numberOfUsers) {
        return numberOfUsers.getValue() <= value;
    }
}
