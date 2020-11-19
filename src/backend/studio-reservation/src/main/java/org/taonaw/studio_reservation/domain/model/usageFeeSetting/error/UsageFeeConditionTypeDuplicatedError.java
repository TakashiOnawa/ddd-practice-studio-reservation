package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeConditionType;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsageFeeConditionTypeDuplicatedError extends Error {
    private final Set<UsageFeeConditionType> errorUsageFeeConditionTypes = new HashSet<>();

    public UsageFeeConditionTypeDuplicatedError(@NonNull List<UsageFeeConditionType> errorUsageFeeConditionTypes) {
        super("利用料金条件区分が重複しています。");
        this.errorUsageFeeConditionTypes.addAll(errorUsageFeeConditionTypes);
    }
}
