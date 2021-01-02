package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import java.util.List;
import java.util.Optional;

public interface BasicUsageFeeSettingRepository {
    List<BasicUsageFeeSetting> findAll();
    Optional<BasicUsageFeeSetting> findBy(BasicUsageFeeSettingId basicUsageFeeSettingId);
    void add(BasicUsageFeeSetting packFeeSetting);
    void update(BasicUsageFeeSetting packFeeSetting);
}
