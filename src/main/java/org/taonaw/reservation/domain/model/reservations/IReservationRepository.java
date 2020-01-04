package org.taonaw.reservation.domain.model.reservations;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.specificatiions.IReservationValidator;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.List;

public interface IReservationRepository {
    List<Reservation> findAll();
    List<Reservation> findByStudio(@NonNull StudioId studioId);
    List<Reservation> findTimePeriodOverlapped(@NonNull Reservation reservation);
    IReservationValidator getReservationValidator();
    void save(Reservation reservation);
}
