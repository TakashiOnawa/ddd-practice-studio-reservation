package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRate;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CancellationFeeRateDuplicatedError extends Error {
    private final Set<CancellationFeeRate> errorCancellationFeeRates = new HashSet<>();

    public CancellationFeeRateDuplicatedError(@NonNull List<CancellationFeeRate> errorCancellationFeeRates) {
        super("キャンセル料金レートが重複しています。");
        this.errorCancellationFeeRates.addAll(errorCancellationFeeRates);
    }
}
