package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

public class CancellationFeeSetting {
    private CancellationFeeRates cancellationFeeRates;

    public static CancellationFeeSetting reconstruct(CancellationFeeRates cancellationFeeRates) {
        CancellationFeeSetting instance = new CancellationFeeSetting();
        instance.cancellationFeeRates = cancellationFeeRates;
        return instance;
    }

    public CancellationFeeRates cancellationFeeRates() {
        return cancellationFeeRates;
    }
}
