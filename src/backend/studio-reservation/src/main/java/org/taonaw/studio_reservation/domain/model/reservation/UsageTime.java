package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDate;
import org.taonaw.studio_reservation.domain.model.studio.StartTimes;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode
public class UsageTime {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public UsageTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
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
        if (ChronoUnit.MINUTES.between(startDateTime, endDateTime) % 60 != 0) {
            throw new IllegalArgumentException("1 時間単位でなければなりません。");
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isOverlapped(@NonNull UsageTime other) {
        return startDateTime.isBefore(other.endDateTime) && other.startDateTime.isBefore(endDateTime);
    }

    public boolean isPassed(@NonNull LocalDateTime currentDateTime) {
        return !startDateTime.isBefore(currentDateTime);
    }

    public boolean isCancellationFeeFree(@NonNull CancellationFeeRates cancellationFeeRates, @NonNull LocalDateTime currentDateTime) {
        return cancellationFeeRates.isFree(startDateTime, currentDateTime);
    }

    public boolean satisfy(@NonNull OpeningHour openingHour) {
        if (openingHour.allDay())
            return true;
        else
            return !startDateTime.isBefore(openingHour.getStartDateTime()) && !endDateTime.isAfter(openingHour.getEndDateTime());
    }

    public boolean satisfy(@NonNull ReservationStartDate reservationStartDate, LocalDateTime currentDateTime) {
        return !startDateTime.isBefore(reservationStartDate.startDate(currentDateTime).atStartOfDay());
    }

    public boolean satisfy(@NonNull StartTimes startTime) {
        return startDateTime.getMinute() == startTime.getStartMinutes();
    }
}
