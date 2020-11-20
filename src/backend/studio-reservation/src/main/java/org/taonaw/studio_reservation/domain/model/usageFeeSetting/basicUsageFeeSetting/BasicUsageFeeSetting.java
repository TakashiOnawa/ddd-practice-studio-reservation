package org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.practiceTypeSetting.PracticeType;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicUsageFeeSetting {
    private final Map<PracticeType, PracticeTypeBasicUsageFeeSetting> practiceTypeBasicFeeSettings;

    private BasicUsageFeeSetting() {
        practiceTypeBasicFeeSettings = Arrays.stream(PracticeType.values())
                .collect(Collectors.toMap(practiceType -> practiceType, PracticeTypeBasicUsageFeeSetting::new));
    }

    public static BasicUsageFeeSetting reconstruct(List<PracticeTypeBasicUsageFeeSetting> practiceTypeBasicUsageFeeSettings) {
        var instance = new BasicUsageFeeSetting();
        practiceTypeBasicUsageFeeSettings.forEach(
                item -> instance.practiceTypeBasicFeeSettings.put(item.practiceType(), item));
        return instance;
    }

    public void setUsageFeeConditionTypes(@NonNull PracticeType practiceType, @NonNull UsageFeeConditionTypes usageFeeConditionTypes) {
        practiceTypeBasicFeeSettings.get(practiceType).setUsageFeeConditionTypes(usageFeeConditionTypes);
    }

    public void setBasicUsageFees(@NonNull PracticeType practiceType, @NonNull BasicUsageFees basicUsageFees) {
        practiceTypeBasicFeeSettings.get(practiceType).setBasicFees(basicUsageFees);
    }
}
