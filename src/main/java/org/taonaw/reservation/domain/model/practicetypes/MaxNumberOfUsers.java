package org.taonaw.reservation.domain.model.practicetypes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class MaxNumberOfUsers {
    private final int value;

    public MaxNumberOfUsers(int value) {
        Assertion.argumentRange(value, 1, 100);
        this.value = value;
    }
}
