package org.taonaw.identityaccess.domain.model.accounts;

import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

public class AccountName {
    private final String value;

    public AccountName(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Account name is required.");
        Assertion.argumentRange(value, 3, 250);
        this.value = value;
    }
}
