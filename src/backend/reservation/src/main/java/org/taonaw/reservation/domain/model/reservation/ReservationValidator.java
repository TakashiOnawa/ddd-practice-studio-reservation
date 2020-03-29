package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.studio.IStudioRepository;
import org.taonaw.reservation.domain.model.tenant.ITenantRepository;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;

@AllArgsConstructor
public class ReservationValidator {
    private final IStudioRepository studioRepository;
    private final ITenantRepository tenantRepository;
    private final CurrentDate currentDate;

    public void validate(@NonNull Reservation reservation) {
        var studio = studioRepository.findBy(reservation.getStudioId()).orElseThrow();
        var tenant = tenantRepository.get();

        if (!tenant.isMaxNumberOfUsersSatisfiedBy(reservation.getPracticeType(), reservation.getNumberOfUsers())) {
            throw new DomainException(DomainExceptionCodes.OverMaxNumberOfUsers);
        }
        if (!tenant.isReservationStartDateTimeSatisfiedBy(reservation.getPracticeType(), reservation.getUseTime(), currentDate)) {
            throw new DomainException(DomainExceptionCodes.ReservationNotStarted);
        }
        if (!tenant.isOpeningHoursSatisfiedBy(reservation.getUseTime())) {
            throw new DomainException(DomainExceptionCodes.OutOfOpeningHours);
        }
        if (!studio.isStartTimeSatisfiedBy(reservation.getUseTime())) {
            throw new DomainException(DomainExceptionCodes.StartTimeTypeNotSatisfied);
        }
    }
}
