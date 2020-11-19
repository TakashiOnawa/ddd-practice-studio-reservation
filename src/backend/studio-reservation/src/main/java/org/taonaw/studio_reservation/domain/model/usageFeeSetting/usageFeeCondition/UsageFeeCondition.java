package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public abstract class UsageFeeCondition {
    private final UsageFeeConditionType type;

    protected UsageFeeCondition(UsageFeeConditionType type) {
        Assertion.required(type);
        this.type = type;
    }

    public boolean isTypeIn(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return usageFeeConditionTypes.contains(type);
    }

    public boolean isTypeDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.getType() == other.getType();
    }

    public boolean isDuplicated(@NonNull UsageFeeCondition other) {
        return this != other && this.equals(other);
    }
}
