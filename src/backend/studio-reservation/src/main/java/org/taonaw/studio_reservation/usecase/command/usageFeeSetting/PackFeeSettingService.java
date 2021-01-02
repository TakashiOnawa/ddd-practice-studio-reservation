package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSetting;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSettingRepository;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;
import org.taonaw.studio_reservation.usecase.command.exception.PackFeeSettingNotFoundException;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.createPackFeeSetting.CreatePackFeeSettingCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFeeConditionTypes.SetPackFeeConditionTypesCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFees.SetPackFeesCommand;

@AllArgsConstructor
public class PackFeeSettingService {
    @Autowired
    private final PackFeeSettingRepository packFeeSettingRepository;

    public void handle(@NonNull CreatePackFeeSettingCommand command) {
        var packFeeSetting = PackFeeSetting.create(command.getPackName(), command.getApplicablePeriod());

        packFeeSettingRepository.add(packFeeSetting);
    }

    public void handle(@NonNull SetPackFeeConditionTypesCommand command) {
        // TODO: 排他制御

        var packFeeSetting = packFeeSettingRepository.findBy(command.getPackFeeSettingId())
                .orElseThrow(PackFeeSettingNotFoundException::new);

        var errorNotification = new ErrorNotification();

        packFeeSetting.setUsageFeeConditionTypes(command.getUsageFeeConditionTypes(), errorNotification);

        errorNotification.throwIfHasErrors("パック料金条件区分を設定できません。");

        packFeeSettingRepository.update(packFeeSetting);
    }

    public void handle(@NonNull SetPackFeesCommand command) {
        // TODO: 排他制御

        var packFeeSetting = packFeeSettingRepository.findBy(command.getPackFeeSettingId())
                .orElseThrow(PackFeeSettingNotFoundException::new);

        var errorNotification = new ErrorNotification();

        packFeeSetting.setUsageFees(command.getUsageFees(), errorNotification);

        errorNotification.throwIfHasErrors("パック料金を設定できません。");

        packFeeSettingRepository.update(packFeeSetting);
    }
}
