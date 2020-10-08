package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class StartTimeInvalidError extends Error {
    public StartTimeInvalidError() {
        super("開始時間が不正です。");
    }
}
