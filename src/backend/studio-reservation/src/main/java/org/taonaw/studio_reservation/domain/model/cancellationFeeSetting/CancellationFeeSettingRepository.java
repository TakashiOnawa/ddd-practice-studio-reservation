package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

public interface CancellationFeeSettingRepository {
    CancellationFeeSetting get();
    void update(CancellationFeeSetting cancellationFeeSetting);
}
