package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class OutOfOpeningHourError extends Error {
    public OutOfOpeningHourError() {
        super("営業時間外です。");
    }
}
