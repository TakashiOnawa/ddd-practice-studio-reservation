package org.taonaw.facilitymanagement.domain.model.tenantsetting;

import lombok.NonNull;

public interface ITenantSettingRepository {
    TenantSetting get();
    void update(@NonNull TenantSetting tenantSetting);
}
