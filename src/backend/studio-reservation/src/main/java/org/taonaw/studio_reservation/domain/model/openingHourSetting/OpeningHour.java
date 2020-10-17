package org.taonaw.studio_reservation.domain.model.openingHourSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class OpeningHour {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public OpeningHour(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assertion.required(startDateTime);
        Assertion.required(endDateTime);

        if (startDateTime.getSecond() != 0 || startDateTime.getNano() != 0) {
            throw new IllegalArgumentException("開始日時に秒の指定はできません。");
        }
        if (endDateTime.getSecond() != 0 || endDateTime.getNano() != 0) {
            throw new IllegalArgumentException("終了日時に秒の指定はできません。");
        }
        if (!startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("終了日時が開始日時以前です。");
        }
        if (endDateTime.isAfter(startDateTime.plusDays(1))) {
            throw new IllegalArgumentException("24 時間以内である必要があります。");
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean allDay() {
        return endDateTime.equals(startDateTime.plusDays(1));
    }
}
