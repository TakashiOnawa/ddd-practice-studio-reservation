package org.taonaw.identityaccess.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class AccountName {
    private final String firstName;
    private final String lastName;

    public AccountName(@NonNull String firstName, @NonNull String lastName) {
        Assertion.argumentRange(firstName, 1, 50);
        Assertion.argumentRange(lastName, 1, 50);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}
