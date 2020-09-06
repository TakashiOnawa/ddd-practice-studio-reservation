package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

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
}
