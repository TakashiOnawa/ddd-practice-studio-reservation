package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;

import java.util.Calendar;

public class TimePeriodUnitOfUsageSpecification implements IReservationSpecification {
    private final int MIN_TIME_PERIOD_UNIT = 60;

    @Override
    public boolean isSatisfied(Reservation reservation) {
        return isSatisfied(reservation.timePeriodOfUsage());
    }

    public boolean isSatisfied(@NonNull TimePeriodOfUsage timePeriodOfUsage) {
        long startFragmentInMinutes = DateUtils.getFragmentInMinutes(timePeriodOfUsage.getStart(), Calendar.HOUR_OF_DAY);
        long minutes = timePeriodOfUsage.minutes();

        return startFragmentInMinutes % MIN_TIME_PERIOD_UNIT == 0 &&
                minutes % MIN_TIME_PERIOD_UNIT == 0;
    }
}
