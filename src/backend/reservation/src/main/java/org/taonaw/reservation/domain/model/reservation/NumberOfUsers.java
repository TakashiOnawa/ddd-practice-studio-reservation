package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class NumberOfUsers {
    private final int value;

    public NumberOfUsers(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }
}
