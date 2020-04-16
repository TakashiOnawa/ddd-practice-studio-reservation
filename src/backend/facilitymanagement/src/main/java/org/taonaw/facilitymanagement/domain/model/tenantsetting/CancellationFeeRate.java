package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class CancellationFeeRate {
    private final int daysAgo;
    private final double rate;

    public CancellationFeeRate(int daysAgo, double rate) {
        Assertion.argumentMin(daysAgo, 0);
        Assertion.argumentRange(rate, minRate(), maxRate());
        this.daysAgo = daysAgo;
        this.rate = rate;
    }

    public static double minRate() {
        return 0.01;
    }

    public static double maxRate() {
        return 1;
    }
}
