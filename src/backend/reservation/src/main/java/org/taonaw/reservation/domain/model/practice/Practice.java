package org.taonaw.reservation.domain.model.practice;

import lombok.NonNull;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.reservation.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.util.Objects;

public class Practice {
    private final PracticeType practiceType;
    private final MaxNumberOfUsers maxNumberOfUsers;
    private final ReservationStartDateTime reservationStartDateTime;

    public Practice(@NonNull PracticeType practiceType,
                    @NonNull MaxNumberOfUsers maxNumberOfUsers,
                    @NonNull ReservationStartDateTime reservationStartDateTime) {
        this.practiceType = practiceType;
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.reservationStartDateTime = reservationStartDateTime;
    }

    public boolean isMaxNumberOfUsersSatisfiedBy(@NonNull NumberOfUsers numberOfUsers) {
        return maxNumberOfUsers.isSatisfiedBy(numberOfUsers);
    }

    public boolean isReservationStartDateTimeSatisfiedBy(@NonNull UseTime useTime, @NonNull CurrentDate currentDate) {
        return reservationStartDateTime.isSatisfiedBy(useTime, currentDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Practice practice = (Practice) o;
        return practiceType == practice.practiceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(practiceType);
    }
}
