package org.taonaw.reservation.domain.model.reservationsetting;

import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservation.PracticeType;
import org.taonaw.reservation.domain.model.reservation.UseTime;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.util.Objects;

public class ReservationSetting {
    private final StudioId studioId;
    private final PracticeType practiceType;

    private final StartTimeType startTimeType;
    private final MaxNumberOfUsers maxNumberOfUsers;
    private final ReservationStartDateTime reservationStartDateTime;
    private final OpeningHours openingHours;

    public ReservationSetting(@NonNull StudioId studioId,
                              @NonNull PracticeType practiceType,
                              @NonNull StartTimeType startTimeType,
                              @NonNull MaxNumberOfUsers maxNumberOfUsers,
                              @NonNull ReservationStartDateTime reservationStartDateTime,
                              @NonNull OpeningHours openingHours) {
        this.studioId = studioId;
        this.practiceType = practiceType;
        this.startTimeType = startTimeType;
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.reservationStartDateTime = reservationStartDateTime;
        this.openingHours = openingHours;
    }

    public boolean isStartTimeSatisfiedBy(@NonNull UseTime useTime) {
        return startTimeType.isSatisfiedBy(useTime);
    }

    public boolean isMaxNumberOfUsersSatisfiedBy(@NonNull NumberOfUsers numberOfUsers) {
        return maxNumberOfUsers.isSatisfiedBy(numberOfUsers);
    }

    public boolean isReservationStartDateTimeSatisfiedBy(@NonNull UseTime useTime,
                                                         @NonNull CurrentDate currentDate) {
        return reservationStartDateTime.isSatisfiedBy(useTime, currentDate);
    }

    public boolean isOpeningHoursSatisfiedBy(@NonNull UseTime useTime) {
        return openingHours.isSatisfiedBy(useTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationSetting that = (ReservationSetting) o;
        return studioId.equals(that.studioId) &&
                practiceType == that.practiceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioId, practiceType);
    }
}
