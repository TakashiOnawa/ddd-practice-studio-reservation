package org.taonaw.studio_reservation.domain.model.staffAccount;

import java.util.Optional;

public interface StaffAccountRepository {
    Optional<StaffAccount> findBy(LoginId loginId);
    AddResults add(StaffAccount staffAccount);

    enum AddResults {
        SUCCEEDED,
        DUPLICATED;

        public boolean isSucceeded() {
            return this == SUCCEEDED;
        }
    }
}
