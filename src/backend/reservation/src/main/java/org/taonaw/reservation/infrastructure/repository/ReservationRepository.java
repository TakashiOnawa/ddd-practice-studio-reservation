package org.taonaw.reservation.infrastructure.repository;

import com.google.gson.Gson;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.reservations.IReservationRepository;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository implements IReservationRepository {

    private final static List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> findAll() {
        return reservations.stream()
                .map(this::deepCopy)
                .collect(Collectors.toList());
    }

    public List<Reservation> findByStudio(@NonNull StudioId studioId) {
        return reservations.stream()
                .filter(item -> item.studioId().equals(studioId))
                .collect(Collectors.toList());
    }

    public List<Reservation> findTimePeriodOverlapped(@NonNull Reservation reservation) {
        return reservations.stream()
                .filter(item -> item.timePeriodOfUsage().isOverlapping(reservation.timePeriodOfUsage()))
                .collect(Collectors.toList());
    }

    public IReservationValidator getReservationValidator() {
        throw new IllegalCallerException();
    }

    public void save(Reservation reservation) {
        reservations.add(deepCopy(reservation));
    }

    private Reservation deepCopy(Reservation reservation) {
        var gson = new Gson();
        return gson.fromJson(gson.toJson(reservation), Reservation.class);
    }
}
