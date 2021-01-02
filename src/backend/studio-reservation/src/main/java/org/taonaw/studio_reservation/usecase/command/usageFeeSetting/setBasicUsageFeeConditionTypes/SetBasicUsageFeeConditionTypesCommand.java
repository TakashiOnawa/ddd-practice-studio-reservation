package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFeeConditionTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSettingId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;

@Getter
@Builder
@AllArgsConstructor
public class SetBasicUsageFeeConditionTypesCommand {
    private BasicUsageFeeSettingId basicUsageFeeSettingId;
    private UsageFeeConditionTypes usageFeeConditionTypes;
}
