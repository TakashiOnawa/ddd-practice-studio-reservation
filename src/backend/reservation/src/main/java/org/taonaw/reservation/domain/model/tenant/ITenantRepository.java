package org.taonaw.reservation.domain.model.tenant;

import lombok.NonNull;

import java.util.Optional;

public interface ITenantRepository {
    Tenant get();
}
