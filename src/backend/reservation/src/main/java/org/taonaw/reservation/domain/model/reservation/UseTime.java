package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservationsetting.StartTimeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode
public class UseTime {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public UseTime(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("終了日時が開始日時以前です。");
        }
        if (start.getSecond() != 0 || start.getNano() != 0) {
            throw new IllegalArgumentException("開始日時に秒の指定はできません。");
        }
        if (end.getSecond() != 0 || end.getNano() != 0) {
            throw new IllegalArgumentException("終了日時に秒の指定はできません。");
        }
        if (ChronoUnit.MINUTES.between(start, end) % 60 != 0) {
            throw new IllegalArgumentException("1 時間単位でなければなりません。");
        }
        StartTimeType.validateStartTime(start.toLocalTime());

        this.start = start;
        this.end = end;
    }

    public boolean isStartMinutesEquals(int minutes) {
        return start.getMinute() == minutes;
    }

    public boolean inRange(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return !this.start.isBefore(start) && !this.end.isAfter(end);
    }

    public LocalDate getStartDate() {
        return start.toLocalDate();
    }

    public LocalDate getEndDate() {
        return end.toLocalDate();
    }

    public boolean isOverlapped(@NonNull UseTime other) {
        return this.start.compareTo(other.end) < 0 && other.start.compareTo(this.end) < 0;
    }
}
