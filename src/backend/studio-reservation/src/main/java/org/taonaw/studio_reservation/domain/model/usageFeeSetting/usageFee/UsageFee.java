package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public abstract class UsageFee {
    private final int amount;

    protected UsageFee(int amount) {
        Assertion.argumentMin(amount, 0);
        this.amount = amount;
    }
}
