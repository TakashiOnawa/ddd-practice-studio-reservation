package org.taonaw.reservation.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.reservations.IReservationRepository;
import org.taonaw.reservation.query.reservation.IReservationQuery;
import org.taonaw.reservation.query.reservation.ReservationDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReservationQuery implements IReservationQuery {

    @Autowired
    private final IReservationRepository reservationRepository;

    public List<ReservationDto> getReservations(@NonNull Date start, @NonNull Date end) {
        return reservationRepository.findAll().stream()
                .filter(item -> item.timePeriodOfUsage().isOverlapping(start, end))
                .map(item -> new ReservationDto())
                .collect(Collectors.toList());
    }
}
