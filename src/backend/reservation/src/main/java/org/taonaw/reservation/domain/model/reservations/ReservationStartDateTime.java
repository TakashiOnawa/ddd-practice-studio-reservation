package org.taonaw.reservation.domain.model.reservations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.taonaw.reservation.domain.shared.Assertion;
import org.taonaw.reservation.common.date.CurrentDate;

@EqualsAndHashCode
public class ReservationStartDateTime {

    private final int startDateValue;
    private final ReservationStartDateTypes startDateType;
    private final int startHoursValue;

    public ReservationStartDateTime(int startDateValue, @NonNull ReservationStartDateTypes startDateType, int startHoursValue) {
        if (!startDateType.isMinValueSatisfiedBy(startDateValue)) {
            throw new IllegalArgumentException();
        }

        Assertion.argumentRange(startHoursValue, 0, 23);

        this.startDateValue = startDateValue;
        this.startDateType = startDateType;
        this.startHoursValue = startHoursValue;
    }

    public boolean isSatisfiedBy(@NonNull TimePeriodOfUsage timePeriodOfUsage, @NonNull CurrentDate currentDate) {
        var startDate = startDateType.getStartDate(currentDate.Now(), this.startDateValue);
        var startDateTime = DateUtils.addHours(startDate, startHoursValue);
        return timePeriodOfUsage.isStartDatePassedFrom(startDateTime);
    }
}
