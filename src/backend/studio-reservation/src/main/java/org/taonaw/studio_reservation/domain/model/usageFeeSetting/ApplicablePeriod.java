package org.taonaw.studio_reservation.domain.model.usageFeeSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.shared.DateRange;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ApplicablePeriod extends DateRange {
    public ApplicablePeriod(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }
}
