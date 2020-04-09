package org.taonaw.identityaccess.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class AccountId {
    private final String value;

    public static AccountId newId() {
        return new AccountId(UUID.randomUUID().toString());
    }

    public AccountId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Account id is required.");
        this.value = value;
    }
}
