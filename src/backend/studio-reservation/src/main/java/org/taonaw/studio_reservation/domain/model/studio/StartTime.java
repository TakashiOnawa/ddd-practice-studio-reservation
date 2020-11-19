package org.taonaw.studio_reservation.domain.model.studio;

import lombok.Getter;

@Getter
public enum StartTime {
    ON_THE_HOUR(1, 0),
    ON_THE_HALF_HOUR(2, 30);

    private final int value;
    private final int startMinutes;

    StartTime(int value, int startMinutes) {
        this.value = value;
        this.startMinutes = startMinutes;
    }
}
