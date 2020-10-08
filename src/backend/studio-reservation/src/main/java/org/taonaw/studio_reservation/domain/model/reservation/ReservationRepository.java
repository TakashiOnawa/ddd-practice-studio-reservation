package org.taonaw.studio_reservation.domain.model.reservation;

import java.util.List;

public interface ReservationRepository {
    void lock();
    List<Reservation> findBy(UsageTime usageTime);
    void add(Reservation reservation);
}
