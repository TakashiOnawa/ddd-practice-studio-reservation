package org.taonaw.studio_reservation.domain.model.practiceTypeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UserMaxCount {
    private final int value;

    public UserMaxCount(int value) {
        Assertion.argumentRange(value, 1, 99);
        this.value = value;
    }
}
