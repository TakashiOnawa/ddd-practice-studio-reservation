package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;

public class CancellationFeeSetting {
    private CancellationFeeRates cancellationFeeRates = CancellationFeeRates.empty();

    public static CancellationFeeSetting reconstruct(CancellationFeeRates cancellationFeeRates) {
        CancellationFeeSetting instance = new CancellationFeeSetting();
        instance.cancellationFeeRates = cancellationFeeRates;
        return instance;
    }

    public void setCancellationFeeRates(
            @NonNull CancellationFeeRates cancellationFeeRates,
            @NonNull ErrorNotification errorNotification) {

        errorNotification.addError(cancellationFeeRates.validateDuplicated());
        errorNotification.addError(cancellationFeeRates.validateRateNotRise());
        if (errorNotification.noErrors()) {
            this.cancellationFeeRates = cancellationFeeRates;
        }
    }

    public CancellationFeeRates cancellationFeeRates() {
        return cancellationFeeRates;
    }
}
