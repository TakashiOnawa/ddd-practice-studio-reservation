package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UserCountCondition extends UsageFeeCondition {
    private final int userCount;

    public UserCountCondition(int userCount) {
        Assertion.argumentRange(userCount, 1, 99);
        this.userCount = userCount;
    }

    public boolean satisfy(@NonNull UserMaxCount userMaxCount) {
        return userCount <= userMaxCount.getValue();
    }
}
