package org.taonaw.reservation.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taonaw.reservation.application.command.RegisterReservationRequest;
import org.taonaw.reservation.domain.model.accounts.AccountId;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.studios.StudioId;

@Service
public class RegisterReservationAppService {

    private final ReservationRepository reservationRepository;

    public RegisterReservationAppService(@Autowired ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void RegisterReservation(RegisterReservationRequest request) {
        Reservation reservation = Reservation.newReservation(
                new AccountId(request.getAccountId()),
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new ReservationTime(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()));

        new RegisterReservationService(reservationRepository).register(reservation);
    }
}
