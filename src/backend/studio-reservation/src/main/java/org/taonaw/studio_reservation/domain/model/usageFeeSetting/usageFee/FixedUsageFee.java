package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditions;

@Getter
@EqualsAndHashCode(callSuper = true)
public class FixedUsageFee extends UsageFee {
    public FixedUsageFee(UsageFeeConditions usageFeeConditions, Fee fee) {
        super(usageFeeConditions, fee);
    }

    @Override
    protected FixedUsageFee copy(UsageFeeConditions usageFeeConditions, Fee fee) {
        return new FixedUsageFee(usageFeeConditions, fee);
    }
}
