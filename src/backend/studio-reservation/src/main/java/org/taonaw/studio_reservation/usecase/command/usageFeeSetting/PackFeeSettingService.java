package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSetting;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSettingRepository;
import org.taonaw.studio_reservation.usecase.command.exception.PackFeeSettingNotFoundException;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.createPackFeeSetting.CreatePackFeeSettingCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFeeConditionTypes.SetPackFeeConditionTypesCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFees.SetPackFeesCommand;

@AllArgsConstructor
public class PackFeeSettingService {
    @Autowired
    private final PackFeeSettingRepository packFeeSettingRepository;

    public void handle(@NonNull CreatePackFeeSettingCommand command) {
        var packFeeSetting = PackFeeSetting.create(command.getPackName(), command.getUsageFeeConditionTypes());

        packFeeSettingRepository.add(packFeeSetting);
    }

    public void handle(@NonNull SetPackFeeConditionTypesCommand command) {
        // TODO: 排他制御

        var packFeeSetting = packFeeSettingRepository.findBy(command.getPackFeeSettingId())
                .orElseThrow(PackFeeSettingNotFoundException::new);

        packFeeSetting.setUsageFeeConditionTypes(command.getUsageFeeConditionTypes());

        packFeeSettingRepository.update(packFeeSetting);
    }

    public void handle(@NonNull SetPackFeesCommand command) {
        // TODO: 排他制御

        var packFeeSetting = packFeeSettingRepository.findBy(command.getPackFeeSettingId())
                .orElseThrow(PackFeeSettingNotFoundException::new);

        packFeeSetting.setUsageFees(command.getUsageFees());

        packFeeSettingRepository.update(packFeeSetting);
    }
}
