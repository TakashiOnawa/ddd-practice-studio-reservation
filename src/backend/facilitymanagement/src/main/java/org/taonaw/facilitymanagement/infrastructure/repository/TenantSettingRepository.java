package org.taonaw.facilitymanagement.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.facilitymanagement.common.DeepCopy;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.ITenantSettingRepository;
import org.taonaw.facilitymanagement.domain.model.tenantsetting.TenantSetting;

@Repository
public class TenantSettingRepository implements ITenantSettingRepository {
    private static TenantSetting tenantSetting = TenantSetting.defaultSetting();

    @Override
    public TenantSetting get() {
        return DeepCopy.clone(tenantSetting, TenantSetting.class);
    }

    @Override
    public void update(@NonNull TenantSetting tenantSetting) {
        tenantSetting = DeepCopy.clone(tenantSetting, TenantSetting.class);
    }
}
