package org.taonaw.studio_reservation.usecase.command.usageFeeSetting;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting.BasicFeeSettingRepository;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setBandPracticeUsageFee.SetBandPracticeUsageFeeCommand;
import org.taonaw.studio_reservation.usecase.command.usageFeeSetting.setPersonalPracticeUsageFee.SetPersonalPracticeUsageFeeCommand;

@AllArgsConstructor
public class UsageFeeSettingService {
    @Autowired
    private final BasicFeeSettingRepository basicFeeSettingRepository;

    public void handle(@NonNull SetBandPracticeUsageFeeCommand command) {
        var basicFeeSetting = basicFeeSettingRepository.get();

        basicFeeSetting.setBandPracticeBasicFees(command.getBasicFees());

        basicFeeSettingRepository.update(basicFeeSetting);
    }

    public void handle(@NonNull SetPersonalPracticeUsageFeeCommand command) {
        var basicFeeSetting = basicFeeSettingRepository.get();

        basicFeeSetting.setPersonalPracticeBasicFees(command.getBasicFees());

        basicFeeSettingRepository.update(basicFeeSetting);
    }
}
