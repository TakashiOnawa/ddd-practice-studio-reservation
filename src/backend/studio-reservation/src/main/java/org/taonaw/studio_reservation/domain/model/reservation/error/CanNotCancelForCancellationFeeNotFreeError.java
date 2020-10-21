package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotCancelForCancellationFeeNotFreeError extends Error {
    public CanNotCancelForCancellationFeeNotFreeError() {
        super("キャンセル料金がかかるため予約をキャンセルできません。");
    }
}
