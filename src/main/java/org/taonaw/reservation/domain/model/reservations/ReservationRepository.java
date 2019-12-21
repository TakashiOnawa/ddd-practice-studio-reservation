package org.taonaw.reservation.domain.model.reservations;

import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.List;

public interface ReservationRepository {
    public List<Reservation> findAll();
    public List<Reservation> findByStudio(StudioId studioId);
    public void save(Reservation reservation);
}
