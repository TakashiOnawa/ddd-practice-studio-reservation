package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CancellationFeeRates {
    private final List<CancellationFeeRate> cancellationFeeRates = new ArrayList<>();

    public CancellationFeeRates(@NonNull List<CancellationFeeRate> cancellationFeeRates) {
        this.cancellationFeeRates.addAll(cancellationFeeRates);
    }
}
