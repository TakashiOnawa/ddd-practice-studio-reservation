package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDateTime;
import org.taonaw.studio_reservation.domain.model.studio.StartTimes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode
public class UsageTime {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public UsageTime(@NonNull LocalDateTime startDateTime, @NonNull LocalDateTime endDateTime) {
        if (startDateTime.getSecond() != 0 || startDateTime.getNano() != 0) {
            throw new IllegalArgumentException("開始日時に秒の指定はできません。");
        }
        if (endDateTime.getSecond() != 0 || endDateTime.getNano() != 0) {
            throw new IllegalArgumentException("終了日時に秒の指定はできません。");
        }
        if (!startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("終了日時が開始日時以前です。");
        }
        if (ChronoUnit.MINUTES.between(startDateTime, endDateTime) % 60 != 0) {
            throw new IllegalArgumentException("1 時間単位でなければなりません。");
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isOverlapped(@NonNull UsageTime other) {
        return startDateTime.isBefore(other.endDateTime) && other.startDateTime.isBefore(endDateTime);
    }

    public boolean satisfy(@NonNull OpeningHour openingHour) {
        return false;
    }

    public boolean satisfy(@NonNull ReservationStartDateTime reservationStartDateTime) {
        return false;
    }

    public boolean satisfy(@NonNull StartTimes startTime) {
        return false;
    }
}
