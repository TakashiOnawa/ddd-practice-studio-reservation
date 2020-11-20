package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class DateTimeRange {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assertion.required(startDateTime);
        Assertion.required(endDateTime);

        if (!startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("終了日時が開始日時以前です。");
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isIn(@NonNull DateTimeRange other) {
        return !startDateTime.isBefore(other.startDateTime) && !endDateTime.isAfter(other.endDateTime);
    }

    public boolean isOverlapped(@NonNull DateTimeRange other) {
        return startDateTime.isBefore(other.endDateTime) && other.startDateTime.isBefore(endDateTime);
    }

    public boolean isPassed(@NonNull LocalDateTime currentDateTime) {
        return !startDateTime.isBefore(currentDateTime);
    }
}
