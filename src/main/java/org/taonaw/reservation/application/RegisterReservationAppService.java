package org.taonaw.reservation.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taonaw.common.date.CurrentDate;
import org.taonaw.reservation.application.command.RegisterReservationRequest;
import org.taonaw.reservation.domain.model.accounts.AccountId;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.reservations.specificatiions.MaxNumberOfUsersSpecification;
import org.taonaw.reservation.domain.model.reservations.specificatiions.ReceptionStartTimeSpecification;
import org.taonaw.reservation.domain.model.reservations.specificatiions.ReservationSpecifications;
import org.taonaw.reservation.domain.model.reservations.specificatiions.TimePeriodUnitOfUsageSpecification;
import org.taonaw.reservation.domain.model.studios.StudioId;

@Service
public class RegisterReservationAppService {

    private final CurrentDate currentDate;
    private final IReservationRepository reservationRepository;

    public RegisterReservationAppService(@Autowired CurrentDate currentDate,
                                         @Autowired IReservationRepository reservationRepository) {
        this.currentDate = currentDate;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void RegisterReservation(RegisterReservationRequest request) {

        ReservationSpecifications reservationSpecifications = new ReservationSpecifications(
                new TimePeriodUnitOfUsageSpecification(),
                new ReceptionStartTimeSpecification(this.currentDate),
                new MaxNumberOfUsersSpecification()
        );

        Reservation reservation = Reservation.newReservation(
                new AccountId(request.getAccountId()),
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()),
                reservationSpecifications);

        new RegisterReservationService(reservationRepository).register(reservation);
    }
}
