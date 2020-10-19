package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class CancellationFeeRate {
    private final int daysAgo;
    private final double rate;

    public CancellationFeeRate(int daysAgo, double rate) {
        Assertion.argumentRange(daysAgo, 0, 50);
        Assertion.argumentRange(rate, 0, 100);
        this.daysAgo = daysAgo;
        this.rate = rate;
    }
}
