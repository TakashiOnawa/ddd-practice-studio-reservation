package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;

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

    public boolean isApplied(@NonNull LocalDateTime targetDateTime, @NonNull LocalDateTime currentDateTime) {
        var appliedDate = targetDateTime.toLocalDate().minusDays(daysAgo);
        var currentDate = currentDateTime.toLocalDate();
        return currentDate.isEqual(appliedDate) || currentDate.isAfter(appliedDate);
    }

    public boolean isFree() {
        return rate == 0;
    }

    public boolean isDuplicated(@NonNull CancellationFeeRate other) {
        return this != other && this.daysAgo == other.daysAgo;
    }
}
