package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class NotStartReservationError extends Error {
    public NotStartReservationError() {
        super("予約受付を開始していません。");
    }
}
