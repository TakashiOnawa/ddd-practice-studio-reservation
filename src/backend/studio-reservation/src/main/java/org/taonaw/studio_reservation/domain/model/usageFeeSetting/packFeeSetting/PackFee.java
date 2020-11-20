package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import lombok.EqualsAndHashCode;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFee;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditions;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

@EqualsAndHashCode
public class PackFee {
    private final UsageFeeConditions usageFeeConditions;
    private final UsageFee usageFee;

    public PackFee(UsageFeeConditions usageFeeConditions, UsageFee usageFee) {
        Assertion.required(usageFeeConditions);
        Assertion.required(usageFee);
        usageFeeConditions.validateTypeDuplicated().ifPresent(Error::throwError);
        this.usageFeeConditions = usageFeeConditions;
        this.usageFee = usageFee;
    }
}
