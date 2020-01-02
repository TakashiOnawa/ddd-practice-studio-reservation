package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.taonaw.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.practicetypes.PracticeTypes;
import org.taonaw.reservation.domain.model.reservations.Reservation;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;

import java.util.Date;

public class ReceptionStartTimeSpecification implements IReservationSpecification {
    private final CurrentDate currentDate;

    public ReceptionStartTimeSpecification(@NonNull CurrentDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public boolean isSatisfied(@NonNull Reservation reservation) {
        return isSatisfied(reservation.practiceType(), reservation.timePeriodOfUsage());
    }

    public boolean isSatisfied(@NonNull PracticeTypes practiceType, @NonNull TimePeriodOfUsage timePeriodOfUsage) {
        Date receptionStartTime = timePeriodOfUsage.startDayOfYear();

        if (practiceType.equals(PracticeTypes.PERSONAL)) {
            receptionStartTime = DateUtils.addDays(receptionStartTime, -1);
            receptionStartTime = DateUtils.addHours(receptionStartTime, 21);
        } else if (practiceType.equals(PracticeTypes.BAND)) {
            receptionStartTime = DateUtils.addMonths(receptionStartTime, -2);
        }

        return !this.currentDate.Now().before((receptionStartTime));
    }
}
