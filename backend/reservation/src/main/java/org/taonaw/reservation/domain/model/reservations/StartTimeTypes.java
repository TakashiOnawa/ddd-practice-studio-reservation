package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public enum StartTimeTypes {
    OnTheHour(0),
    OnTheHalfHour(30);

    private final int startMinutes;

    public boolean isSatisfiedBy(@NonNull TimePeriodOfUsage timePeriodOfUsage) {
        return timePeriodOfUsage.isStartMinutesEquals(startMinutes)
                && timePeriodOfUsage.isPeriodEquals(60);
    }
}
