package org.taonaw.studio_reservation.domain.model.reservation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeRates;
import org.taonaw.studio_reservation.domain.model.openingHourSetting.OpeningHour;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.ReservationStartDate;
import org.taonaw.studio_reservation.domain.model.shared.DateTimeRange;
import org.taonaw.studio_reservation.domain.model.studio.StartTime;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UsageTime extends DateTimeRange {
    public UsageTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(startDateTime, endDateTime);
        Assertion.required(startDateTime);
        Assertion.required(endDateTime);

        if (startDateTime.getSecond() != 0 || startDateTime.getNano() != 0) {
            throw new IllegalArgumentException("開始日時に秒の指定はできません。");
        }
        if (endDateTime.getSecond() != 0 || endDateTime.getNano() != 0) {
            throw new IllegalArgumentException("終了日時に秒の指定はできません。");
        }
        if (ChronoUnit.MINUTES.between(startDateTime, endDateTime) % 60 != 0) {
            throw new IllegalArgumentException("1 時間単位でなければなりません。");
        }
    }

    public boolean isCancellationFeeFree(@NonNull CancellationFeeRates cancellationFeeRates, @NonNull LocalDateTime currentDateTime) {
        return cancellationFeeRates.isFree(getStartDateTime(), currentDateTime);
    }

    public boolean satisfy(@NonNull OpeningHour openingHour) {
        if (openingHour.isAllDay())
            return true;
        else
            return this.isIn(openingHour.toDateTimeRange(getStartDateTime().toLocalDate()));
    }

    public boolean satisfy(@NonNull ReservationStartDate reservationStartDate, LocalDateTime currentDateTime) {
        return !getStartDateTime().isBefore(reservationStartDate.startDate(currentDateTime).atStartOfDay());
    }

    public boolean satisfy(@NonNull StartTime startTime) {
        return getStartDateTime().getMinute() == startTime.getStartMinutes();
    }
}
