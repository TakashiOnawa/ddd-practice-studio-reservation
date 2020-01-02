package org.taonaw.reservation.domain.model.studios;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StartTimeTypes {
    OnTheHour(0),
    OnTheHalfHour(30);

    private final int startMinutes;
}
