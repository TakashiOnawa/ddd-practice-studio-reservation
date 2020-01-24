package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.taonaw.reservation.domain.shared.specification.ISpecification;
import org.taonaw.reservation.domain.model.reservations.Reservation;

public interface IReservationValidator extends ISpecification<Reservation> {
    void validate(@NonNull Reservation reservation);
}
