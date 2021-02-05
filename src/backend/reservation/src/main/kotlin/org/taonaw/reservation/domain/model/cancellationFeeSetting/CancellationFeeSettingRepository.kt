package org.taonaw.reservation.domain.model.cancellationFeeSetting

interface CancellationFeeSettingRepository {
    fun find(): CancellationFeeSetting
}