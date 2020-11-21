package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditions;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

@Getter
@EqualsAndHashCode
public abstract class UsageFee {
    private final UsageFeeConditions usageFeeConditions;
    private final Fee fee;

    public UsageFee(UsageFeeConditions usageFeeConditions, Fee fee) {
        Assertion.required(usageFeeConditions);
        Assertion.required(fee);
        usageFeeConditions.validateTypeDuplicated().ifPresent(Error::throwError);
        this.usageFeeConditions = usageFeeConditions;
        this.fee = fee;
    }

    protected abstract UsageFee copy(UsageFeeConditions usageFeeConditions, Fee fee);

    public boolean isUsageFeeConditionsTypeIn(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return usageFeeConditions.isTypeIn(usageFeeConditionTypes);
    }

    public boolean isDuplicated(@NonNull UsageFee other) {
        return this != other &&
                usageFeeConditions.isDuplicated(other.usageFeeConditions);
    }

    public UsageFee removeBasicUsageFeeCondition(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return copy(usageFeeConditions.remove(usageFeeConditionTypes), fee);
    }
}
