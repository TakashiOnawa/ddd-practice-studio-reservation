package org.taonaw.reservation.infrastructure.repository;

import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.tenant.ITenantRepository;
import org.taonaw.reservation.domain.model.tenant.Tenant;

@Repository
public class TenantRepository implements ITenantRepository {
    @Override
    public Tenant get() {
        return null;
    }
}
