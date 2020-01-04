package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.common.domain.exception.DomainException;
import org.taonaw.reservation.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.domain.model.reservations.MaxNumberOfUsersSpecification;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.ReservationStartDateTimeSpecification;
import org.taonaw.reservation.domain.model.reservations.StartTimeSpecification;

@AllArgsConstructor
public class ReservationValidationSpecification implements IReservationValidator {

    @NonNull
    private final MaxNumberOfUsersSpecification maxNumberOfUsersSpecification;
    @NonNull
    private final ReservationStartDateTimeSpecification reservationStartDateTimeSpecification;
    @NonNull
    private final StartTimeSpecification startTimeSpecification;

    @Override
    public boolean isSatisfiedBy(@NonNull Reservation reservation) {
        return maxNumberOfUsersSpecification.isSatisfiedBy(reservation)
                && reservationStartDateTimeSpecification.isSatisfiedBy(reservation)
                && startTimeSpecification.isSatisfiedBy(reservation);
    }

    @Override
    public void validate(@NonNull Reservation reservation) {
        if (!maxNumberOfUsersSpecification.isSatisfiedBy(reservation)) {
            throw new DomainException(DomainExceptionCodes.OverMaxNumberOfUsers);
        }
        if (!reservationStartDateTimeSpecification.isSatisfiedBy(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationNotStarted);
        }
        if (!startTimeSpecification.isSatisfiedBy(reservation)) {
            throw new DomainException(DomainExceptionCodes.StartTimeTypeNotSatisfied);
        }
    }
}
