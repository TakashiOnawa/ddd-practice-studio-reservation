package org.taonaw.reservation.domain.model.tenant;

import lombok.NonNull;
import org.taonaw.reservation.domain.model.reservation.UseTime;

import java.util.Objects;

public class Tenant {
    private final TenantId tenantId;
    private final OpeningHours openingHours;

    public Tenant(@NonNull TenantId tenantId,
                  @NonNull OpeningHours openingHours) {
        this.tenantId = tenantId;
        this.openingHours = openingHours;
    }

    public boolean isOpeningHoursSatisfiedBy(@NonNull UseTime useTime) {
        return openingHours.isSatisfiedBy(useTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return tenantId.equals(tenant.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId);
    }
}
