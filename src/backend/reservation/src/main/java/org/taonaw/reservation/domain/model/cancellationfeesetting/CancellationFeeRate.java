package org.taonaw.reservation.domain.model.cancellationfeesetting;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CancellationFeeRate {
    private final int daysAgo;
    private final double rate;

    public boolean isApplied(@NonNull UseTime useTime, @NonNull LocalDate currentDate) {
        var appliedDate = useTime.getStartDate().minusDays(daysAgo);
        return currentDate.isEqual(appliedDate) || currentDate.isAfter(appliedDate);
    }

    public boolean isFree() {
        return rate == 0;
    }

    public boolean isNotFree() {
        return !isFree();
    }
}
