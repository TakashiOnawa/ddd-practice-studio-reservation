package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.createPackFeeSetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackName;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;

@Getter
@Builder
@AllArgsConstructor
public class CreatePackFeeSettingCommand {
    private PackName packName;
    private UsageFeeConditionTypes usageFeeConditionTypes;
}
