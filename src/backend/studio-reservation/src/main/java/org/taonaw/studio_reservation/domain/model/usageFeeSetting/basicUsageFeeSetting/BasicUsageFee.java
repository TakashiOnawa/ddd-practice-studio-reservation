package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFee;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditions;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

@EqualsAndHashCode
public class BasicUsageFee {
    private final UsageFeeConditions usageFeeConditions;
    private final UsageFee usageFee;

    public BasicUsageFee(UsageFeeConditions usageFeeConditions, UsageFee usageFee) {
        Assertion.required(usageFeeConditions);
        Assertion.required(usageFee);
        usageFeeConditions.validateTypeDuplicated().ifPresent(Error::throwError);
        this.usageFeeConditions = usageFeeConditions;
        this.usageFee = usageFee;
    }

    public boolean isUsageFeeConditionsTypeIn(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return usageFeeConditions.isTypeIn(usageFeeConditionTypes);
    }

    public boolean isDuplicated(@NonNull BasicUsageFee other) {
        return this != other &&
                usageFeeConditions.isDuplicated(other.usageFeeConditions);
    }

    public BasicUsageFee removeBasicUsageFeeCondition(@NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        return new BasicUsageFee(usageFeeConditions.remove(usageFeeConditionTypes), usageFee);
    }
}
