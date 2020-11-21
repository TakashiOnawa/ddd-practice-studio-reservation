package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditions;

@Getter
@EqualsAndHashCode(callSuper = true)
public class HourlyUsageFee extends UsageFee {
    public HourlyUsageFee(UsageFeeConditions usageFeeConditions, Fee fee) {
        super(usageFeeConditions, fee);
    }

    @Override
    protected HourlyUsageFee copy(UsageFeeConditions usageFeeConditions, Fee fee) {
        return new HourlyUsageFee(usageFeeConditions, fee);
    }
}
