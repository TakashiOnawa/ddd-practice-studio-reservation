package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservationsetting.IReservationSettingRepository;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;

@AllArgsConstructor
public class ReservationValidator {
    private final IReservationSettingRepository reservationSettingRepository;
    private final CurrentDate currentDate;

    public void validate(@NonNull Reservation reservation) {
        var reservationSetting = reservationSettingRepository
                .findBy(reservation.getStudioId(), reservation.getPracticeType());

        if (!reservationSetting.isMaxNumberOfUsersSatisfiedBy(reservation.getNumberOfUsers())) {
            throw new DomainException(DomainExceptionCodes.OverMaxNumberOfUsers);
        }
        if (!reservationSetting.isReservationStartDateTimeSatisfiedBy(reservation.getUseTime(), currentDate)) {
            throw new DomainException(DomainExceptionCodes.ReservationNotStarted);
        }
        if (!reservationSetting.isOpeningHoursSatisfiedBy(reservation.getUseTime())) {
            throw new DomainException(DomainExceptionCodes.OutOfOpeningHours);
        }
        if (!reservationSetting.isStartTimeSatisfiedBy(reservation.getUseTime())) {
            throw new DomainException(DomainExceptionCodes.StartTimeTypeNotSatisfied);
        }
    }
}
