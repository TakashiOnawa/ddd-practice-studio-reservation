package org.taonaw.reservation.domain.model.tenant;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class TenantId {

    private final String value;

    public TenantId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Tenant id is required.");
        this.value = value;
    }
}
