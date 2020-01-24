package org.taonaw.authentication.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@EqualsAndHashCode
public class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(@NonNull String firstName, @NonNull String lastName) {
        Assertion.argumentNotEmpty(firstName, "First name is required.");
        Assertion.argumentRange(firstName, 1, 50);
        Assertion.argumentNotEmpty(lastName, "Last name is required.");
        Assertion.argumentRange(lastName, 1, 50);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
