package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CancellationFeeRates {
    private final List<CancellationFeeRate> items;

    public CancellationFeeRates(List<CancellationFeeRate> items) {
        Assertion.required(items);
        // TODO: 何日前の値は重複してはならない。
        // TODO: 何日前の値の順にソートして保持する。
        // TODO: キャンセル料金のレートは徐々に高くなっていかなけれがならない。
        this.items = new ArrayList<>(items);
    }

    public static CancellationFeeRates empty() {
        return new CancellationFeeRates(new ArrayList<>());
    }

    public List<CancellationFeeRate> items() {
        return new ArrayList<>(items);
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
