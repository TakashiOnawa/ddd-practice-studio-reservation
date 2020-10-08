package org.taonaw.studio_reservation.domain.model.reservation.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class ReservationDuplicatedError extends Error {
    public ReservationDuplicatedError() {
        super("予約が重複しています。");
    }
}
