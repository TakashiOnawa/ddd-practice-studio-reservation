package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.Reservation;

public interface IReservationSpecification {
    boolean isSatisfied(@NonNull Reservation reservation);
}
