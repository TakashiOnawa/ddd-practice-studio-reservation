package org.taonaw.reservation.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.member.MemberId;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

import java.util.Map;

@Getter
public class CanNotCancelReservationException extends MultipleDomainException {
    private final String reservationId;

    private CanNotCancelReservationException(Map<Class<? extends Reason>, Reason> reasons, String reservationId) {
        super("予約をキャンセルできません。", reasons, true);
        this.reservationId = reservationId;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CancelByDifferentMember extends Reason {
        private final String memberId;

        @Override
        public String getMessage() {
            return "異なる会員によるキャンセルはできません。";
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ThereIsCancellationFee extends Reason {
        @Override
        public String getMessage() {
            return "キャンセル料がかかるためキャンセルできません。";
        }
    }

    @AllArgsConstructor
    public static class Builder extends MultipleDomainException.Builder {
        private final ReservationId reservationId;

        public Builder cancelByDifferentMember(@NonNull MemberId memberId) {
            putReason(new CancelByDifferentMember(memberId.getValue()));
            return this;
        }

        public Builder thereIsCancellationFee() {
            putReason(new ThereIsCancellationFee());
            return this;
        }

        @Override
        protected MultipleDomainException createDomainException(@NonNull Map<Class<? extends Reason>, Reason> reasons) {
            return new CanNotCancelReservationException(reasons, reservationId.getValue());
        }
    }
}
