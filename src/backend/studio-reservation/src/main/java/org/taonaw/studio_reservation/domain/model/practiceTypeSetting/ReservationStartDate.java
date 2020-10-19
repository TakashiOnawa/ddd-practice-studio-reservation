package org.taonaw.studio_reservation.domain.model.practiceTypeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class ReservationStartDate {
    private final int startDateValue;
    private final ReservationStartDateTypes startDateType;

    public ReservationStartDate(int startDateValue, ReservationStartDateTypes startDateType) {
        Assertion.required(startDateType);
        this.startDateValue = startDateValue;
        this.startDateType = startDateType;
    }

    public LocalDate startDate(@NonNull LocalDateTime currentDateTime) {
        return startDateType.startDate(currentDateTime, startDateValue);
    }
}
