package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import java.util.Optional;

public interface PackFeeSettingRepository {
    Optional<PackFeeSetting> findBy(PackFeeSettingId packFeeSettingId);
    void add(PackFeeSetting packFeeSetting);
    void update(PackFeeSetting packFeeSetting);
}
