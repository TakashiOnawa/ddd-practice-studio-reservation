package org.taonaw.reservation.domain.model.reservations.specificatiions;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservations.NumberOfUsers;
import org.taonaw.reservation.domain.model.reservations.PracticeTypes;
import org.taonaw.reservation.domain.model.reservations.Reservation;

public class MaxNumberOfUsersSpecification implements IReservationSpecification {

    @Override
    public boolean isSatisfied(@NonNull Reservation reservation) {
        return isSatisfied(reservation.practiceType(), reservation.numberOfUsers());
    }

    public boolean isSatisfied(@NonNull PracticeTypes practiceType, @NonNull NumberOfUsers numberOfUsers) {
        if (practiceType.equals(PracticeTypes.PERSONAL)) {
            if (numberOfUsers.greatherThan(2)) {
                return false;
            }
        }
        return true;
    }
}
