package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.tenant.ITenantRepository;
import org.taonaw.reservation.domain.model.tenant.Tenant;
import org.taonaw.reservation.domain.model.tenant.TenantId;

import java.util.Optional;

@Repository
public class TenantRepository implements ITenantRepository {
    @Override
    public Tenant get() {
        return null;
    }

    @Override
    public Optional<Tenant> findBy(@NonNull TenantId tenantId) {
        return Optional.empty();
    }
}
