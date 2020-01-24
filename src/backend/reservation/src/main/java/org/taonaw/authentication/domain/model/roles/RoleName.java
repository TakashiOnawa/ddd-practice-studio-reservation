package org.taonaw.authentication.domain.model.roles;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@EqualsAndHashCode
public class RoleName {
    private final String value;

    public RoleName(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Role name is required.");
        Assertion.argumentRange(value, 1, 20);
        this.value = value;
    }
}
