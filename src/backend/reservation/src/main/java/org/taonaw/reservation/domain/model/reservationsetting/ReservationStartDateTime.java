package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservation.UseTime;

@EqualsAndHashCode
public class ReservationStartDateTime {
    private final int startDateValue;
    private final ReservationStartDateType startDateType;
    private final int startHour;

    public ReservationStartDateTime(int startDateValue,
                                    @NonNull ReservationStartDateType startDateType,
                                    int startHour) {
        this.startDateValue = startDateValue;
        this.startDateType = startDateType;
        this.startHour = startHour;
    }

    public boolean isSatisfiedBy(@NonNull UseTime useTime, @NonNull CurrentDate currentDate) {
        var reservableDateTime =  startDateType
                .getStartDate(useTime.getStart(), startDateValue).atTime(startHour, 0);
        return !currentDate.isBefore(reservableDateTime);
    }
}
