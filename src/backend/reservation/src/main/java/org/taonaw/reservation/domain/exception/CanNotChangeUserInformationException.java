package org.taonaw.reservation.domain.exception;

import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

@Getter
public class CanNotChangeUserInformationException extends DomainException {
    private final String reservationId;

    public CanNotChangeUserInformationException(@NonNull ReservationId reservationId) {
        super("会員の利用者情報は変更できません。");
        this.reservationId = reservationId.getValue();
    }
}
