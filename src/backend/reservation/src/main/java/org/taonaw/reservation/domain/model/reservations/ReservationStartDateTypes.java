package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor()
public enum ReservationStartDateTypes {
    // ありえないが、10 年前を最小値にしておく。
    DaysAgo(1, 3650, Calendar.DATE),
    WeeksAgo(2, 522, Calendar.WEEK_OF_MONTH),
    MonthsAgo(3, 120, Calendar.YEAR);

    private final int value;
    private final int minValue;
    private final int calendarField;

    public boolean isMinValueSatisfiedBy(int value) {
        return this.minValue <= value;
    }

    public Date getStartDate(Date currentDate, int startDateValue) {
        var currentDateCalendar = DateUtils.toCalendar(currentDate);
        currentDateCalendar.add(this.calendarField, startDateValue);

        var startDate = currentDateCalendar.getTime();
        DateUtils.truncate(startDate, calendarField);
        return startDate;
    }
}
