package org.taonaw.authentication.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.Calendar;
import java.util.Date;

@EqualsAndHashCode
public class DateOfBirth {
    private final int year;
    private final int month;
    private final int day;

    public DateOfBirth(@NonNull Date date) {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public DateOfBirth(int year, int month, int day) {
        Assertion.argumentRange(year, 1, 3000);
        Assertion.argumentRange(month, 1, 12);
        Assertion.argumentRange(day, 1, getLastDay(year, month));
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private int getLastDay(int year, int month) {
        var calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.getMaximum(Calendar.DAY_OF_MONTH);
    }
}
