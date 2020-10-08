package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UserCount {
    private final int value;

    public UserCount(int value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }

    public boolean satisfy(@NonNull UserMaxCount userMaxCount) {
        return false;
    }
}
