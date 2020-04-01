package org.taonaw.identityaccess.domain.model.role;

import lombok.NonNull;
import org.taonaw.identityaccess.domain.shared.Assertion;

import java.util.UUID;

public class RoleId {
    private final String value;

    RoleId() {
        this(UUID.randomUUID().toString());
    }

    public RoleId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Role id is required.");
        this.value = value;
    }
}
