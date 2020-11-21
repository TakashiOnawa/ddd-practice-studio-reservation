package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPackFees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSettingId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;

@Getter
@Builder
@AllArgsConstructor
public class SetPackFeesCommand {
    private PackFeeSettingId packFeeSettingId;
    private UsageFees usageFees;
}
