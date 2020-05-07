package org.taonaw.reservation.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

@Getter
public class CanNotChangeUseTimeException extends DomainException {
    private final String reservationId;

    public CanNotChangeUseTimeException(@NonNull ReservationId reservationId) {
        super("キャンセル料がかかるため利用時間の変更はできません。");
        this.reservationId = reservationId.getValue();
    }
}
