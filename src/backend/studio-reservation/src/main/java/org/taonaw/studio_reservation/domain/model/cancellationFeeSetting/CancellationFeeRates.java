package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CancellationFeeRates {
    private final List<CancellationFeeRate> items = new ArrayList<>();

    public CancellationFeeRates(@NonNull List<CancellationFeeRate> items) {
        // TODO: 何日前の値は重複してはならない。
        // TODO: 何日前の値の順にソートして保持する。
        // TODO: キャンセル料金のレートは徐々に高くなっていかなけれがならない。
        this.items.addAll(items);
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
