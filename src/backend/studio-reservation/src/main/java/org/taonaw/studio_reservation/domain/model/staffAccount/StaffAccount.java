package org.taonaw.studio_reservation.domain.model.staffAccount;

import org.taonaw.studio_reservation.domain.model.shared.HashedPassword;
import org.taonaw.studio_reservation.domain.model.shared.PasswordEncoder;
import org.taonaw.studio_reservation.domain.model.shared.PersonName;
import org.taonaw.studio_reservation.domain.model.shared.PlainTextPassword;

import java.util.Objects;

public class StaffAccount {
    private StaffAccountId id;
    private PersonName name;
    private LoginId loginId;
    private HashedPassword password;

    public boolean authenticate(LoginId loginId, PlainTextPassword plainTextPassword, PasswordEncoder passwordEncoder) {
        return this.loginId.equals(loginId) && plainTextPassword.matches(this.password, passwordEncoder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffAccount that = (StaffAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
