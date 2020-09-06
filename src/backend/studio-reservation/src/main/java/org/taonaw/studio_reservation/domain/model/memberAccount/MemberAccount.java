package org.taonaw.studio_reservation.domain.model.memberAccount;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.shared.*;

import java.util.Objects;

public class MemberAccount {
    private MemberAccountId id;
    private PersonName name;
    private EmailAddress emailAddress;
    private HashedPassword password;
    private DateOfBirth dateOfBirth;
    private PhoneNumber phoneNumber;

    public boolean authenticate(
            @NonNull EmailAddress emailAddress,
            @NonNull PlainTextPassword plainTextPassword,
            @NonNull PasswordEncoder passwordEncoder) {

        return this.emailAddress.equals(emailAddress) && plainTextPassword.matches(this.password, passwordEncoder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccount that = (MemberAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
