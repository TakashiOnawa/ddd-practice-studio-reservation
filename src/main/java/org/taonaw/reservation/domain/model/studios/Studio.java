package org.taonaw.reservation.domain.model.studios;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;

public class Studio {
    private final StudioId studioId;
    private final StartTimeTypes startTimeType;

    public Studio(@NonNull StudioId studioId,
                   @NonNull StartTimeTypes startTimeType) {
        this.studioId = studioId;
        this.startTimeType = startTimeType;
    }

    public boolean startTimeSatisfied(TimePeriodOfUsage timePeriodOfUsage) {
        return true;
    }
}
