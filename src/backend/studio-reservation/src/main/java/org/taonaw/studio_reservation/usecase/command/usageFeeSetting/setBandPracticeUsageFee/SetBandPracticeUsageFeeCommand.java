package org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBandPracticeUsageFee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting.BasicFee;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SetBandPracticeUsageFeeCommand {
    private List<BasicFee> basicFees;
}
