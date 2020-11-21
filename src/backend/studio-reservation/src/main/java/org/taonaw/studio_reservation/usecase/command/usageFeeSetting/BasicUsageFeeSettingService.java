package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSettingRepository;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFeeConditionTypes.SetBasicUsageFeeConditionTypesCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFees.SetBasicUsageFeesCommand;

@AllArgsConstructor
public class BasicUsageFeeSettingService {
    @Autowired
    private final BasicUsageFeeSettingRepository basicUsageFeeSettingRepository;

    public void handle(@NonNull SetBasicUsageFeeConditionTypesCommand command) {
        // TODO: 排他制御

        var basicUsageFeeSetting = basicUsageFeeSettingRepository.get();

        var errorNotification = new ErrorNotification();

        basicUsageFeeSetting.setUsageFeeConditionTypes(
                command.getPracticeType(),
                command.getUsageFeeConditionTypes(),
                errorNotification);

        errorNotification.throwIfHasErrors("基本利用料金条件区分を設定できません。");

        basicUsageFeeSettingRepository.update(basicUsageFeeSetting);
    }

    public void handle(@NonNull SetBasicUsageFeesCommand command) {
        // TODO: 排他制御

        var basicFeeSetting = basicUsageFeeSettingRepository.get();

        var errorNotification = new ErrorNotification();

        basicFeeSetting.setBasicUsageFees(
                command.getPracticeType(),
                command.getUsageFees(),
                errorNotification);

        errorNotification.throwIfHasErrors("基本利用料金を設定できません。");

        basicUsageFeeSettingRepository.update(basicFeeSetting);
    }
}
