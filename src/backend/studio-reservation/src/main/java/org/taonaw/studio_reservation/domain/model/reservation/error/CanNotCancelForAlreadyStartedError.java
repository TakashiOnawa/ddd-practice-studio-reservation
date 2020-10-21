package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotCancelForAlreadyStartedError extends Error {
    public CanNotCancelForAlreadyStartedError() {
        super("利用開始日時を過ぎているため予約をキャンセルできません。");
    }
}
