package org.taonaw.identityaccess.domain.model.roles;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

@EqualsAndHashCode
public class RoleName {
    private final String value;

    public RoleName(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Role name is required.");
        Assertion.argumentRange(value, 1, 20);
        this.value = value;
    }
}
