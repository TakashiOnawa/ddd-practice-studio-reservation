package org.taonaw.authentication.domain.model.accounts;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@EqualsAndHashCode
public class Password {
    private final String value;

    public Password(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Password is required.");
        Assertion.argumentRange(value, 8, 20);
        // TODO:英数記号のみで、英数が 1 つ以上含まれている必要がある。
        this.value = value;
    }
}
