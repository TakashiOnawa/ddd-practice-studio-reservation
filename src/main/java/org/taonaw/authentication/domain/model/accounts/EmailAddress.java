package org.taonaw.authentication.domain.model.accounts;

import lombok.NonNull;
import org.taonaw.common.Assertion;

public class EmailAddress {
    private  final String value;

    public EmailAddress(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Email address is required.");
        Assertion.argumentRange(value, 1, 100);
        Assertion.argumentPatternMatches(
                value,
                "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", "Email address format is invalid.");
        this.value = value;
    }
}
