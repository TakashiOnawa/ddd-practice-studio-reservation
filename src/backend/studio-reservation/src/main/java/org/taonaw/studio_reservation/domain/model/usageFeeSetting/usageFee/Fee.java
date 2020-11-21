package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class Fee {
    private final int amount;

    protected Fee(int amount) {
        Assertion.argumentMin(amount, 0);
        this.amount = amount;
    }
}
