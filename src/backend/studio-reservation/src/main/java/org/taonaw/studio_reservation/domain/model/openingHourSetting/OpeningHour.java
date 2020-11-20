package org.taonaw.studio_reservation.domain.model.openingHourSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.TimeRange;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalTime;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OpeningHour extends TimeRange {
    public OpeningHour(LocalTime startTime, LocalTime endTime) {
        super(startTime, endTime);
        Assertion.required(startTime);
        Assertion.required(endTime);

        if (hasStartTimeSeconds()) {
            throw new IllegalArgumentException("開始時間に秒の指定はできません。");
        }
        if (hasEndTimeSeconds()) {
            throw new IllegalArgumentException("終了時間に秒の指定はできません。");
        }
    }

    public boolean isAllDay() {
        return getStartTime().equals(getEndTime());
    }
}
