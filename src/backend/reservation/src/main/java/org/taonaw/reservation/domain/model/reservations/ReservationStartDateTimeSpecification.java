package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.shared.specification.ISpecification;

import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class ReservationStartDateTimeSpecification implements ISpecification<Reservation> {

    @NonNull
    private final Map<PracticeTypes, ReservationStartDateTime> reservationStartDateTimeMap;
    @NonNull
    private  final CurrentDate currentDate;

    @Override
    public boolean isSatisfiedBy(@NonNull Reservation reservation) {
        return isSatisfiedBy(reservation.practiceType(), reservation.timePeriodOfUsage());
    }

    boolean isSatisfiedBy(@NonNull PracticeTypes practiceType, @NonNull TimePeriodOfUsage timePeriodOfUsage) {
        var reservationStartDateTime = reservationStartDateTimeMap.get(practiceType);
        if (reservationStartDateTime == null) {
            throw new NoSuchElementException("practice type is not exists.");
        }

        return reservationStartDateTime.isSatisfiedBy(timePeriodOfUsage, this.currentDate);
    }
}
