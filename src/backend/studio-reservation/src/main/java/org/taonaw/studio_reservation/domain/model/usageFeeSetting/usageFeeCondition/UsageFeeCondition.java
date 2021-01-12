package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public abstract class UsageFeeCondition {
    public UsageFeeConditionType getConditionType() {
        return UsageFeeConditionType.of(this);
    }

    public boolean isConditionTypeDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.getConditionType() == other.getConditionType();
    }

    public boolean isDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.equals(other);
    }
}
