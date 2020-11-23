package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CancellationFeeRateNotRiseError extends Error {
    public CancellationFeeRateNotRiseError() {
        super("キャンセル料金レートは徐々に高くならなければなりません。");
    }
}
