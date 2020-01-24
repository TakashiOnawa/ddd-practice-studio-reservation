package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.specification.ISpecification;

import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class MaxNumberOfUsersSpecification implements ISpecification<Reservation> {

    @NonNull
    private final Map<PracticeTypes, MaxNumberOfUsers> maxNumberOfUsersMap;

    @Override
    public boolean isSatisfiedBy(@NonNull Reservation reservation) {
        return isSatisfied(reservation.practiceType(), reservation.numberOfUsers());
    }

    boolean isSatisfied(@NonNull PracticeTypes practiceType, @NonNull NumberOfUsers numberOfUsers) {
        var maxNumberOfUsers = maxNumberOfUsersMap.get(practiceType);
        if (maxNumberOfUsers == null) {
            throw new NoSuchElementException("practice type is not exists.");
        }

        return maxNumberOfUsers.isSatisfiedBy(numberOfUsers);
    }
}
