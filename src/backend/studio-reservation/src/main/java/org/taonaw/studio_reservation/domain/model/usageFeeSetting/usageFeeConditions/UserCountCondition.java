package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeConditions;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.UserMaxCount;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class UserCountCondition implements UsageFeeCondition {
    private final int userCount;

    public UserCountCondition(int userCount) {
        Assertion.argumentRange(userCount, 1, 99);
        this.userCount = userCount;
    }

    public boolean satisfy(@NonNull UserMaxCount userMaxCount) {
        return userCount <= userMaxCount.getValue();
    }
}
