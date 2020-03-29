package org.taonaw.reservation.domain.model.tenant;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservation.UseTime;
import org.taonaw.reservation.domain.shared.Assertion;

import java.time.LocalTime;

@EqualsAndHashCode
public class ReservationStartDateTime {
    private final int daysAgo;
    private final LocalTime time;

    public ReservationStartDateTime(int daysAgo, LocalTime time) {
        Assertion.argumentMin(daysAgo, 0);
        this.daysAgo = daysAgo;
        this.time = time;
    }

    public boolean isSatisfiedBy(@NonNull UseTime useTime, @NonNull CurrentDate currentDate) {
        var reservableDateTime =  useTime.daysAgo(daysAgo).toLocalDate().atTime(time);
        return !currentDate.isBefore(reservableDateTime);
    }
}
