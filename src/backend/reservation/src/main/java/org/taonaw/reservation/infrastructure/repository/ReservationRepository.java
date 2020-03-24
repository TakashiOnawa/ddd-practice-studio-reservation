package org.taonaw.reservation.infrastructure.repository;

import com.google.gson.Gson;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
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
    public Optional<Reservation> findBy(@NonNull ReservationId reservationId) {
        var reservation = reservations.stream()
                .filter(item -> item.getReservationId().equals(reservationId))
                .findFirst();
        if (reservation.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(deepCopy(reservation.get()));
    }

    @Override
    public List<Reservation> findByDateRange(@NonNull LocalDate start, @NonNull LocalDate end) {
        var startDateTime = start.atStartOfDay();
        var endDateTime = end.atTime(LocalTime.MAX);
        return reservations.stream()
                .filter(item -> item.getUseTime().inRange(startDateTime, endDateTime))
                .map(this::deepCopy)
                .collect(Collectors.toList());
    }

    @Override
    public void add(@NonNull Reservation reservation) {
        reservations.add(deepCopy(reservation));
    }

    private Reservation deepCopy(Reservation reservation) {
        var gson = new Gson();
        return gson.fromJson(gson.toJson(reservation), Reservation.class);
    }
}
