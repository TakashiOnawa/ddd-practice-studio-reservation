package org.taonaw.reservation.domain.model.reservations;

import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.List;

public interface IReservationRepository {
    List<Reservation> findAll();
    List<Reservation> findByStudio(StudioId studioId);
    void save(Reservation reservation);
}
