package org.taonaw.reservation.domain.model.practicetypes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ReservationStartDateTime {
    @NonNull
    private final ReservationStartDate reservationStartDate;
    @NonNull
    private final ReservationStartTime reservationStartTime;
}
