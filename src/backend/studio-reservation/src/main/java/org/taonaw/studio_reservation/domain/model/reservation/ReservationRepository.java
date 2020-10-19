package org.taonaw.studio_reservation.domain.model.reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    void lock();
    Optional<Reservation> findBy(ReservationId reservationId);
    List<Reservation> findBy(UsageTime usageTime);
    void add(Reservation reservation);
}
