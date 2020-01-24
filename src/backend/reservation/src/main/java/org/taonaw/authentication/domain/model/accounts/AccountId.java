package org.taonaw.authentication.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class AccountId {
    private final String value;

    AccountId() {
        this(UUID.randomUUID().toString());
    }

    public AccountId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Account id is required.");
        this.value = value;
    }
}
