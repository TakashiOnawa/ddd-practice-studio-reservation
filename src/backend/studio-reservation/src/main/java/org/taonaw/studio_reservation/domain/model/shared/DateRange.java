package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        Assertion.required(startDate);
        Assertion.required(endDate);

        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("終了日が開始日以前です。");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
