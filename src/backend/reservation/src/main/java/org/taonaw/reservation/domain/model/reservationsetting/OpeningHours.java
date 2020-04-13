package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalTime;

@EqualsAndHashCode
@AllArgsConstructor
public class OpeningHours {
    @NonNull
    private final LocalTime start;
    @NonNull
    private final LocalTime end;

    public boolean isSatisfiedBy(@NonNull UseTime useTime) {
        var usableStart = useTime.getStart().toLocalDate().atTime(start);
        var usableEnd = useTime.getEnd().toLocalDate().atTime(end);
        if (!start.isBefore(end)) {
            usableEnd = usableEnd.plusDays(1);
        }
        return useTime.inRange(usableStart, usableEnd);
    }
}
