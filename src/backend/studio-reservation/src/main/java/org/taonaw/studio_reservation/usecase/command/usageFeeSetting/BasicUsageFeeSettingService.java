package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSetting;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSettingRepository;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;
import org.taonaw.studio_reservation.usecase.command.exception.BasicUsageFeeSettingNotFoundException;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.createBasicUsageFeeSetting.CreateBasicUsageFeeSettingCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFeeConditionTypes.SetBasicUsageFeeConditionTypesCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFees.SetBasicUsageFeesCommand;

@AllArgsConstructor
public class BasicUsageFeeSettingService {
    @Autowired
    private final BasicUsageFeeSettingRepository basicUsageFeeSettingRepository;

    public void handle(@NonNull CreateBasicUsageFeeSettingCommand command) {
        var basicUsageFeeSetting = BasicUsageFeeSetting.create(command.getPracticeType(), command.getApplicablePeriod());

        basicUsageFeeSettingRepository.add(basicUsageFeeSetting);
    }

    public void handle(@NonNull SetBasicUsageFeeConditionTypesCommand command) {
        // TODO: 排他制御

        var basicUsageFeeSetting = basicUsageFeeSettingRepository.findBy(command.getBasicUsageFeeSettingId())
                .orElseThrow(BasicUsageFeeSettingNotFoundException::new);

        var errorNotification = new ErrorNotification();

        basicUsageFeeSetting.setUsageFeeConditionTypes(command.getUsageFeeConditionTypes(), errorNotification);

        errorNotification.throwIfHasErrors("基本利用料金条件区分を設定できません。");

        basicUsageFeeSettingRepository.update(basicUsageFeeSetting);
    }

    public void handle(@NonNull SetBasicUsageFeesCommand command) {
        // TODO: 排他制御

        var basicUsageFeeSetting = basicUsageFeeSettingRepository.findBy(command.getBasicUsageFeeSettingId())
                .orElseThrow(BasicUsageFeeSettingNotFoundException::new);

        var errorNotification = new ErrorNotification();

        basicUsageFeeSetting.setUsageFees(command.getUsageFees(), errorNotification);

        errorNotification.throwIfHasErrors("基本利用料金を設定できません。");

        basicUsageFeeSettingRepository.update(basicUsageFeeSetting);
    }
}
