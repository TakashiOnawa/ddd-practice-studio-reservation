package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class CanNotChangeForAlreadyStartedError extends Error {
    public CanNotChangeForAlreadyStartedError() {
        super("利用開始日時を過ぎているため予約を変更できません。");
    }
}
