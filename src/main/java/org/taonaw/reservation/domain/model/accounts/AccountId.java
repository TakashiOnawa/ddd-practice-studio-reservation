package org.taonaw.reservation.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class AccountId {
    private final String value;

    public AccountId() {
        this.value = UUID.randomUUID().toString();
    }

    public AccountId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
