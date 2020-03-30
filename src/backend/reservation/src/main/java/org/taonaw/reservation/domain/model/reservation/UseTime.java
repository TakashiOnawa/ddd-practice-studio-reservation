package org.taonaw.reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservationsetting.StartTimeType;
import org.taonaw.reservation.domain.shared.Assertion;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class UseTime {
    private final LocalDateTime start;
    private final int hourQuantity;

    public UseTime(@NonNull LocalDateTime start, int hourQuantity) {
        Assertion.argumentMin(hourQuantity, 1);
        StartTimeType.validateStartTime(start.toLocalTime());
        this.start = start;
        this.hourQuantity = hourQuantity;
    }

    public boolean isStartMinutesEquals(int minutes) {
        return start.getMinute() == minutes;
    }

    public LocalDateTime getEnd() {
        return start.plusHours(hourQuantity);
    }

    public boolean inRange(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return !this.start.isBefore(start) && !this.getEnd().isAfter(end);
    }

    public LocalDate getStartDate() {
        return start.toLocalDate();
    }

    public LocalDate getEndDate() {
        return getEnd().toLocalDate();
    }

    public boolean isOverlapped(@NonNull UseTime other) {
        return this.start.compareTo(other.getEnd()) < 0 && other.start.compareTo(this.getEnd()) < 0;
    }
}
