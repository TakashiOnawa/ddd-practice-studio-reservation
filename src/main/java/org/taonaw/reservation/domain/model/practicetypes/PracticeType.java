package org.taonaw.reservation.domain.model.practicetypes;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservations.TimePeriodOfUsage;

public class PracticeType {
    private final PracticeTypes practiceType;
    private final ReservationStartDateTime reservationStartDateTime;
    private final MaxNumberOfUsers maxNumberOfUsers;

    public PracticeType(@NonNull PracticeTypes practiceType,
                        @NonNull ReservationStartDateTime reservationStartDateTime,
                        @NonNull MaxNumberOfUsers maxNumberOfUsers) {
        this.practiceType = practiceType;
        this.reservationStartDateTime = reservationStartDateTime;
        this.maxNumberOfUsers = maxNumberOfUsers;
    }

    public boolean beforeReservation(@NonNull TimePeriodOfUsage timePeriodOfUsage) {
        return true;
    }

    public boolean overMaxNumberOfUsers(@NonNull NumberOfUsers numberOfUsers) {
        return true;
    }
}
