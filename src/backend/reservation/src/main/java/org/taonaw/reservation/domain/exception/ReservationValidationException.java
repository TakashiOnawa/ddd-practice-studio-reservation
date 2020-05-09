package org.taonaw.reservation.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalDateTime;
import java.util.Map;

public class ReservationValidationException extends MultipleDomainException {
    private ReservationValidationException(Map<Class<? extends Reason>, Reason> reasons) {
        super("予約の内容が不正です。", reasons, true);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OverMaxNumberOfUsers extends Reason {
        private final int numberOfUsers;

        @Override
        public String getMessage() {
            return "利用最大人数を超えています。";
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ReservationNotStarted extends Reason {
        @NonNull private final LocalDateTime startDateTime;
        @NonNull private final LocalDateTime endDateTime;

        @Override
        public String getMessage() {
            return "予約を開始していません。";
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OutOfOpeningHours extends Reason {
        @NonNull private final LocalDateTime startDateTime;
        @NonNull private final LocalDateTime endDateTime;

        @Override
        public String getMessage() {
            return "営業時間外の予約はできません。";
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class StartTimeTypeNotSatisfied extends Reason {
        @NonNull private final LocalDateTime startDateTime;
        @NonNull private final LocalDateTime endDateTime;

        @Override
        public String getMessage() {
            return "スタジオのスタート時間を満たしていません。";
        }
    }

    public static class Builder extends MultipleDomainException.Builder {
        public Builder overMaxNumberOfUsers(@NonNull NumberOfUsers numberOfUsers) {
            putReason(new OverMaxNumberOfUsers(numberOfUsers.getValue()));
            return this;
        }

        public Builder reservationNotStarted(@NonNull UseTime useTime) {
            putReason(new ReservationNotStarted(useTime.getStart(), useTime.getEnd()));
            return this;
        }

        public Builder outOfOpeningHours(@NonNull UseTime useTime) {
            putReason(new OutOfOpeningHours(useTime.getStart(), useTime.getEnd()));
            return this;
        }

        public Builder startTimeTypeNotSatisfied(@NonNull UseTime useTime) {
            putReason(new StartTimeTypeNotSatisfied(useTime.getStart(), useTime.getEnd()));
            return this;
        }

        @Override
        protected MultipleDomainException createDomainException(@NonNull Map<Class<? extends Reason>, Reason> reasons) {
            return new ReservationValidationException(reasons);
        }
    }
}
