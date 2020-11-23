package org.taonaw.studio_reservation.usecase.command.cancellationFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeSettingRepository;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;
import org.taonaw.studio_reservation.usecase.command.cancellationFeeSetting.setCancellationFeeRates.SetCancellationFeeRatesCommand;

@AllArgsConstructor
public class CancellationFeeSettingService {
    @Autowired
    private final CancellationFeeSettingRepository cancellationFeeSettingRepository;

    public void handle(@NonNull SetCancellationFeeRatesCommand command) {
        // TODO: 排他制御

        var cancellationFeeSetting = cancellationFeeSettingRepository.get();

        var errorNotification = new ErrorNotification();

        cancellationFeeSetting.setCancellationFeeRates(command.getCancellationFeeRates(), errorNotification);

        errorNotification.throwIfHasErrors("キャンセル料金レートを設定できません。");

        cancellationFeeSettingRepository.update(cancellationFeeSetting);
    }
}
