package org.taonaw.reservation.domain.model.reservation;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservationRepository {
    void lock();
    Optional<Reservation> findBy(@NonNull ReservationId reservationId);
    List<Reservation> findByDateRange(@NonNull LocalDate start, @NonNull LocalDate end);
    void add(@NonNull Reservation reservation);
    void update(@NonNull Reservation reservation);
}
