package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import java.util.List;
import java.util.Optional;

public interface PackFeeSettingRepository {
    List<PackFeeSetting> findAll();
    Optional<PackFeeSetting> findBy(PackFeeSettingId packFeeSettingId);
    void add(PackFeeSetting packFeeSetting);
    void update(PackFeeSetting packFeeSetting);
}
