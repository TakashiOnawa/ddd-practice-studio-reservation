package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CancellationFeeRates {
    private final List<CancellationFeeRate> items = new ArrayList<>();

    private CancellationFeeRates() {
    }

    public CancellationFeeRates(@NonNull List<CancellationFeeRate> cancellationFeeRates) {
        this.items.addAll(cancellationFeeRates);
    }

    public CancellationFeeRates copy() {
        var copy = new CancellationFeeRates();
        copy.items.addAll(this.items);
        return copy;
    }
}
