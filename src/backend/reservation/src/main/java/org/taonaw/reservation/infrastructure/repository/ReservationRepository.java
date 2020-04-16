package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.common.DeepCopy;
import org.taonaw.reservation.domain.model.reservation.IReservationRepository;
import org.taonaw.reservation.domain.model.reservation.Reservation;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository implements IReservationRepository {
    private final static Map<ReservationId, Reservation> values = new ConcurrentHashMap<>();
    private final static Map<ReservationId, Reservation> canceledValues = new ConcurrentHashMap<>();

    @Override
    public void lock() {

    }

    @Override
    public Optional<Reservation> findBy(@NonNull ReservationId reservationId) {
        var reservation = values.get(reservationId);
        if (reservation == null) {
            return Optional.empty();
        }
        return Optional.of(DeepCopy.clone(reservation, Reservation.class));
    }

    @Override
    public List<Reservation> findByDateRange(@NonNull LocalDate start, @NonNull LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return values.values().stream()
                .filter(item -> item.getUseTime().inRange(startDateTime, endDateTime))
                .map(item -> DeepCopy.clone(item, Reservation.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(@NonNull Reservation reservation) {
        values.put(reservation.getReservationId(), DeepCopy.clone(reservation, Reservation.class));
    }

    @Override
    public void update(@NonNull Reservation reservation) {
        if (reservation.isCanceled()) {
            values.remove(reservation.getReservationId());
            canceledValues.put(reservation.getReservationId(), DeepCopy.clone(reservation, Reservation.class));
            return;
        }
        values.put(reservation.getReservationId(), DeepCopy.clone(reservation, Reservation.class));
    }
}
