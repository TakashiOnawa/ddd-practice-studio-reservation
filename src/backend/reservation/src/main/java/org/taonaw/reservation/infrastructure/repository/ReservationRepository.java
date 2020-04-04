package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.common.DeepCopy;
import org.taonaw.reservation.domain.model.reservation.IReservationRepository;
import org.taonaw.reservation.domain.model.reservation.Reservation;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository implements IReservationRepository {
    private final static List<Reservation> reservations = new ArrayList<>();

    @Override
    public void lock() {

    }

    @Override
    public Optional<Reservation> findBy(@NonNull ReservationId reservationId) {
        var reservation = reservations.stream()
                .filter(item -> item.getReservationId().equals(reservationId))
                .findFirst();
        if (reservation.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(reservation.get(), Reservation.class));
    }

    @Override
    public List<Reservation> findByDateRange(@NonNull LocalDate start, @NonNull LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return reservations.stream()
                .filter(item -> item.getUseTime().inRange(startDateTime, endDateTime))
                .map(item -> DeepCopy.clone(item, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(@NonNull Reservation reservation) {
        reservations.add(DeepCopy.clone(reservation, Reservation.class));
    }
}
