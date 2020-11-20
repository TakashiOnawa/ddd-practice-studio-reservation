package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
public class TimeRange {
    private final LocalTime startTime;
    private final LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        Assertion.required(startTime);
        Assertion.required(endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean hasStartTimeSeconds() {
        return startTime.getSecond() != 0 || startTime.getNano() != 0;
    }

    public boolean hasEndTimeSeconds() {
        return endTime.getSecond() != 0 || endTime.getNano() != 0;
    }

    public boolean isStraddleTheDay() {
        return !startTime.isBefore(endTime);
    }

    public DateTimeRange toDateTimeRange(@NonNull LocalDate date) {
        var startDateTime = date.atTime(getStartTime());
        var endDateTime = isStraddleTheDay() ? date.plusDays(1).atTime(getEndTime()) : date.atTime(getEndTime());
        return new DateTimeRange(startDateTime, endDateTime);
    }

    public boolean isOverlapped(@NonNull TimeRange other) {
        var sampleDate = LocalDate.now();

        var thisStartDateTime = sampleDate.atTime(this.startTime);
        var thisEndDateTime = this.isStraddleTheDay() ?
                sampleDate.plusDays(1).atTime(this.endTime) :
                sampleDate.atTime(this.endTime);

        var otherStartDateTime = sampleDate.atTime(other.startTime);
        var otherEndDateTime = other.isStraddleTheDay() ?
                sampleDate.plusDays(1).atTime(other.endTime) :
                sampleDate.atTime(other.endTime);

        return thisStartDateTime.isBefore(otherEndDateTime) && otherStartDateTime.isBefore(thisEndDateTime);
    }
}
