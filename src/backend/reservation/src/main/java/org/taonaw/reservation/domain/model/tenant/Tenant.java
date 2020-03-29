package org.taonaw.reservation.domain.model.tenant;

import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservation.PracticeType;
import org.taonaw.reservation.domain.model.reservation.UseTime;

public class Tenant {
    private final OpeningHours openingHours;
    private final MaxNumberOfUsers personalPracticeMaxNumberOfUsers;
    private final ReservationStartDateTime bandReservationStartDateTime;
    private final ReservationStartDateTime personalReservationStartDateTime;

    public Tenant(@NonNull OpeningHours openingHours,
                  @NonNull MaxNumberOfUsers personalPracticeMaxNumberOfUsers,
                  @NonNull ReservationStartDateTime bandReservationStartDateTime,
                  @NonNull ReservationStartDateTime personalReservationStartDateTime) {
        this.openingHours = openingHours;
        this.personalPracticeMaxNumberOfUsers = personalPracticeMaxNumberOfUsers;
        this.bandReservationStartDateTime = bandReservationStartDateTime;
        this.personalReservationStartDateTime = personalReservationStartDateTime;
    }

    public boolean isOpeningHoursSatisfiedBy(@NonNull UseTime useTime) {
        return openingHours.isSatisfiedBy(useTime);
    }

    public boolean isMaxNumberOfUsersSatisfiedBy(@NonNull PracticeType practiceType,
                                                 @NonNull NumberOfUsers numberOfUsers) {
        if (practiceType.equals(PracticeType.PERSONAL)) {
            return personalPracticeMaxNumberOfUsers.isSatisfiedBy(numberOfUsers);
        }
        return true;
    }

    public boolean isReservationStartDateTimeSatisfiedBy(@NonNull PracticeType practiceType,
                                                         @NonNull UseTime useTime,
                                                         @NonNull CurrentDate currentDate) {
        if (practiceType.equals(PracticeType.BAND)) {
            return bandReservationStartDateTime.isSatisfiedBy(useTime, currentDate);
        }
        if (practiceType.equals(PracticeType.PERSONAL)) {
            return personalReservationStartDateTime.isSatisfiedBy(useTime, currentDate);
        }
        return true;
    }
}
