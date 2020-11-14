package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting;

public interface BasicFeeSettingRepository {
    BasicFeeSetting get();
    void update(BasicFeeSetting basicFeeSetting);
}
