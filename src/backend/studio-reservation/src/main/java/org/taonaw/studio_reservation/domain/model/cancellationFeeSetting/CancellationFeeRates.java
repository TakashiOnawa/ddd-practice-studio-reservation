package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.error.CancellationFeeRateDuplicatedError;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.error.CancellationFeeRateNotRiseError;
import org.taonaw.studio_reservation.domain.shared.Assertion;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CancellationFeeRates {
    private final List<CancellationFeeRate> items;

    public CancellationFeeRates(List<CancellationFeeRate> items) {
        Assertion.required(items);

        items.sort((o1, o2) -> o2.getDaysAgo() - o1.getDaysAgo());
        this.items = new ArrayList<>(items);
    }

    public static CancellationFeeRates empty() {
        return new CancellationFeeRates(new ArrayList<>());
    }

    public List<CancellationFeeRate> items() {
        return new ArrayList<>(items);
    }

    public Optional<Error> validateDuplicated() {
        var errorItems = items.stream()
                .filter(item -> items.stream().anyMatch(item::isDuplicated))
                .collect(Collectors.toList());
        if (errorItems.isEmpty())
            return Optional.empty();
        else
            return Optional.of(new CancellationFeeRateDuplicatedError(errorItems));
    }

    public Optional<Error> validateRateNotRise() {
        var currentRate = 0D;
        for (var item : items) {
            if (currentRate > item.getRate()) {
                return Optional.of(new CancellationFeeRateNotRiseError());
            }
            currentRate = item.getRate();
        }
        return Optional.empty();
    }

    public boolean isFree(@NonNull LocalDateTime targetDateTime, @NonNull LocalDateTime currentDateTime) {
        for (var cancellationFeeRate : items) {
            if (cancellationFeeRate.isApplied(targetDateTime, currentDateTime) && !cancellationFeeRate.isFree()) {
                return false;
            }
        }
        return true;
    }
}
