package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSettingRepository;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFeeConditionTypes.SetBasicUsageFeeConditionTypesCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFees.SetBasicUsageFeesCommand;

@AllArgsConstructor
public class BasicUsageFeeSettingService {
    @Autowired
    private final BasicUsageFeeSettingRepository basicUsageFeeSettingRepository;

    public void handle(@NonNull SetBasicUsageFeeConditionTypesCommand command) {
        // TODO: 排他制御

        var basicUsageFeeSetting = basicUsageFeeSettingRepository.get();

        basicUsageFeeSetting.setUsageFeeConditionTypes(command.getPracticeType(), command.getUsageFeeConditionTypes());

        basicUsageFeeSettingRepository.update(basicUsageFeeSetting);
    }

    public void handle(@NonNull SetBasicUsageFeesCommand command) {
        // TODO: 排他制御

        var basicFeeSetting = basicUsageFeeSettingRepository.get();

        basicFeeSetting.setBasicUsageFees(command.getPracticeType(), command.getUsageFees());

        basicUsageFeeSettingRepository.update(basicFeeSetting);
    }
}