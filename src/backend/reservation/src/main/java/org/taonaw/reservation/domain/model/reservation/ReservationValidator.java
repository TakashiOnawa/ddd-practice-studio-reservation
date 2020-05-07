package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.exception.ReservationValidationException;
import org.taonaw.reservation.domain.model.reservationsetting.IReservationSettingRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ReservationValidator {
    private final IReservationSettingRepository reservationSettingRepository;
    private final LocalDateTime currentDateTime;

    public void validate(@NonNull Reservation reservation) {
        var reservationSetting = reservationSettingRepository
                .findBy(reservation.getStudioId(), reservation.getPracticeType());

        var exceptionBuilder = new ReservationValidationException.Builder();

        if (!reservationSetting.isMaxNumberOfUsersSatisfiedBy(reservation.getNumberOfUsers())) {
            exceptionBuilder.overMaxNumberOfUsers(reservation.getNumberOfUsers());
        }
        if (!reservationSetting.isReservationStartDateTimeSatisfiedBy(reservation.getUseTime(), currentDateTime)) {
            exceptionBuilder.reservationNotStarted(reservation.getUseTime());
        }
        if (!reservationSetting.isOpeningHoursSatisfiedBy(reservation.getUseTime())) {
            exceptionBuilder.outOfOpeningHours(reservation.getUseTime());
        }
        if (!reservationSetting.isStartTimeSatisfiedBy(reservation.getUseTime())) {
            exceptionBuilder.startTimeTypeNotSatisfied(reservation.getUseTime());
        }

        exceptionBuilder.throwIfErrorExists();
    }
}
