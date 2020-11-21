package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFeeConditionTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSettingId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;

@Getter
@Builder
@AllArgsConstructor
public class SetPackFeeConditionTypesCommand {
    private PackFeeSettingId packFeeSettingId;
    private UsageFeeConditionTypes usageFeeConditionTypes;
}
