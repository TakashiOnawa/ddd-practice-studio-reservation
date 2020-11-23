package org.taonaw.studio_reservation.domain.model.reservation;

import java.util.Optional;

public interface ReservationRepository {
    void lock();
    Optional<Reservation> findBy(ReservationId reservationId);
    Reservations findBy(UsageTime usageTime);
    void add(Reservation reservation);
    UpdateResults update(Reservation reservation);

    enum UpdateResults {
        SUCCEEDED,
        CHANGED_BY_OTHER;

        public boolean isSucceeded() {
            return this == SUCCEEDED;
        }
    }
}
