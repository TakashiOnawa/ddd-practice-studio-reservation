package org.taonaw.reservation.domain.model.cancellationfeesetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
public class CancellationFeeSetting {
    @NonNull
    private final Set<CancellationFeeRate> cancellationFeeRates;

    public boolean isFree(@NonNull UseTime useTime, @NonNull LocalDate currentDate) {
        for (var cancellationFeeRate : cancellationFeeRates) {
            if (cancellationFeeRate.isApplied(useTime, currentDate) && cancellationFeeRate.isNotFree()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotFree(@NonNull UseTime useTime, @NonNull LocalDate currentDate) {
        return !isFree(useTime, currentDate);
    }
}
