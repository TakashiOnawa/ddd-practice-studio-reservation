package org.taonaw.studio_reservation.domain.model.memberAccount;

import org.taonaw.studio_reservation.domain.model.shared.EmailAddress;

import java.util.Optional;

public interface MemberAccountRepository {
    Optional<MemberAccount> findBy(EmailAddress emailAddress);

    AddResults add(MemberAccount memberAccount);

    enum AddResults {
        SUCCEEDED,
        DUPLICATED;

        public boolean isSucceeded() {
            return this == SUCCEEDED;
        }
    }
}
