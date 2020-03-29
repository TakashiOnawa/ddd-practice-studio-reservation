package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class ReservationStartDateTime {
    private final int startDateValue;
    private final ReservationStartDateType startDateType;
    private final int startHours;

    public ReservationStartDateTime(int startDateValue,
                                    @NonNull ReservationStartDateType startDateType,
                                    int startHours) {
        this.startDateValue = startDateValue;
        this.startDateType = startDateType;
        this.startHours = startHours;
    }
}
