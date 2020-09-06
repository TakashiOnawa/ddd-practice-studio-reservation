package org.taonaw.studio_reservation.domain.model.staffAccount;

import lombok.NonNull;
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

    public static StaffAccount create(
            @NonNull PersonName staffName,
            @NonNull LoginId loginId,
            @NonNull HashedPassword hashedPassword) {

        var instance = new StaffAccount();
        instance.id = StaffAccountId.newId();
        instance.name = staffName;
        instance.loginId = loginId;
        instance.password = hashedPassword;
        return instance;
    }

    public boolean authenticate(
            @NonNull LoginId loginId,
            @NonNull PlainTextPassword plainTextPassword,
            @NonNull PasswordEncoder passwordEncoder) {

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
