package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicFeeSetting {
    private final Map<PracticeTypes, PracticeTypeBasicFeeSetting> practiceTypeBasicFeeSettings;

    private BasicFeeSetting() {
        practiceTypeBasicFeeSettings = Arrays.stream(PracticeTypes.values())
                .collect(Collectors.toMap(practiceType -> practiceType, PracticeTypeBasicFeeSetting::new));
    }

    public static BasicFeeSetting reconstruct(List<PracticeTypeBasicFeeSetting> practiceTypeBasicFeeSettings) {
        var instance = new BasicFeeSetting();
        practiceTypeBasicFeeSettings.forEach(item -> instance.practiceTypeBasicFeeSettings.put(item.practiceType(), item));
        return instance;
    }

    public void setBandPracticeBasicFees(@NonNull List<BasicFee> basicFees, BasicFeeSettingRule basicFeeSettingRule) {
        practiceTypeBasicFeeSettings.get(PracticeTypes.BAND).setBasicFees(basicFees, basicFeeSettingRule);
    }

    public void setPersonalPracticeBasicFees(@NonNull List<BasicFee> basicFees, BasicFeeSettingRule basicFeeSettingRule) {
        practiceTypeBasicFeeSettings.get(PracticeTypes.PERSONAL).setBasicFees(basicFees, basicFeeSettingRule);
    }
}
