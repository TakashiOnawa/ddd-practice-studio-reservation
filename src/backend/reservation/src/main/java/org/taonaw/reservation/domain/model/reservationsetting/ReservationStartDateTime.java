package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
public class ReservationStartDateTime {
    private final int startDateValue;
    @NonNull
    private final ReservationStartDateType startDateType;
    private final int startHour;

    public boolean isSatisfiedBy(@NonNull UseTime useTime, @NonNull LocalDateTime currentDateTime) {
        var reservableDateTime =
                startDateType.getStartDate(useTime.getStart(), startDateValue).atTime(startHour, 0);
        return !currentDateTime.isBefore(reservableDateTime);
    }
}
