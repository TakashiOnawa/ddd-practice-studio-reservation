package org.taonaw.studio_reservation.domain.model.usageFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class UsageFee {
    private final int amount;

    public UsageFee(int amount) {
        Assertion.argumentMin(amount, 0);
        this.amount = amount;
    }
}
