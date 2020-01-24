package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.specification.ISpecification;
import org.taonaw.reservation.domain.model.studios.StudioId;

import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class StartTimeSpecification implements ISpecification<Reservation> {

    @NonNull
    private final Map<StudioId, StartTimeTypes> startTimeTypeMap;

    @Override
    public boolean isSatisfiedBy(@NonNull Reservation reservation) {
        return isSatisfiedBy(reservation.studioId(), reservation.timePeriodOfUsage());
    }

    boolean isSatisfiedBy(@NonNull StudioId studioId, @NonNull TimePeriodOfUsage timePeriodOfUsage) {
        var startTimeType = startTimeTypeMap.get(studioId);
        if (startTimeType == null) {
            throw new NoSuchElementException("studio is not exists.");
        }

        return startTimeType.isSatisfiedBy(timePeriodOfUsage);
    }
}
