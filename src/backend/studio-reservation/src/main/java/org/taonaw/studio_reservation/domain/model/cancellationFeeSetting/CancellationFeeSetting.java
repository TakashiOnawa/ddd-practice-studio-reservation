package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import java.util.List;

public class CancellationFeeSetting {
    private CancellationFeeRates cancellationFeeRates;

    public static CancellationFeeSetting reconstruct(List<CancellationFeeRate> cancellationFeeRates) {
        CancellationFeeSetting instance = new CancellationFeeSetting();
        instance.cancellationFeeRates = new CancellationFeeRates(cancellationFeeRates);
        return instance;
    }

    public CancellationFeeRates cancellationFeeRates() {
        return cancellationFeeRates.copy();
    }
}
