package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.reservations.IReservationRepository;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.List;

@Repository
public class ReservationRepository implements IReservationRepository {

    public List<Reservation> findAll() {
        throw new IllegalCallerException();
    }

    public List<Reservation> findByStudio(@NonNull StudioId studioId) {
        throw new IllegalCallerException();
    }

    public List<Reservation> findTimePeriodOverlapped(@NonNull Reservation reservation) {
        throw new IllegalCallerException();
    }

    public IReservationValidator getReservationValidator() {
        throw new IllegalCallerException();
    }

    public void save(Reservation reservation) {
        throw new IllegalCallerException();
    }
}
