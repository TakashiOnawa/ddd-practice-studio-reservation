package org.taonaw.studio_reservation.domain.model.cancellationFeeSetting;

public interface CancellationFeeSettingRepository {
    CancellationFeeSetting find();
    void update(CancellationFeeSetting cancellationFeeSetting);
}
