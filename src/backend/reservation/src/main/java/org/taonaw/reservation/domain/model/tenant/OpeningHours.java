package org.taonaw.reservation.domain.model.tenant;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalTime;

@EqualsAndHashCode
public class OpeningHours {
    private final LocalTime start;
    private final LocalTime end;

    public OpeningHours(@NonNull LocalTime start, @NonNull LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean isSatisfiedBy(@NonNull UseTime useTime) {
        var usableStart = useTime.getStart().toLocalDate().atTime(start);
        var usableEnd = useTime.getEnd().toLocalDate().atTime(end);
        if (!start.isBefore(end)) {
            usableEnd = usableEnd.plusDays(1);
        }
        return useTime.inRange(usableStart, usableEnd);
    }
}
