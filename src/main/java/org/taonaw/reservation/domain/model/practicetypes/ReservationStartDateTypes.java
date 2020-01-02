package org.taonaw.reservation.domain.model.practicetypes;

import lombok.AllArgsConstructor;

@AllArgsConstructor()
public enum ReservationStartDateTypes {
    DaysAgo(1),
    WeeksAgo(2),
    MonthsAgo(3);

    private final int value;
}
