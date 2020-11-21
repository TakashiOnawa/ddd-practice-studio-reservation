package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBasicUsageFees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFees;

@Getter
@Builder
@AllArgsConstructor
public class SetBasicUsageFeesCommand {
    private PracticeType practiceType;
    private UsageFees usageFees;
}
