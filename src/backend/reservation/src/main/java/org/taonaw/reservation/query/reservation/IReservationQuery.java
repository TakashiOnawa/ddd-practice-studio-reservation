package org.taonaw.reservation.query.reservation;

import lombok.NonNull;

import java.util.Date;
import java.util.List;

public interface IReservationQuery {
    List<ReservationDto> getReservations(@NonNull Date start, @NonNull Date end);
}
