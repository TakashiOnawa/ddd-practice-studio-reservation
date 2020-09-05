package org.taonaw.studio_reservation.domain.model.staffAccount;

import java.util.Optional;

public interface StaffAccountRepository {
    Optional<StaffAccount> findBy(LoginId loginId);
}
