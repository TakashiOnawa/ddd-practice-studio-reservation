package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsageFeeConditionDuplicatedError extends Error {
    private final Set<UsageFeeCondition> errorUsageFeeConditions = new HashSet<>();

    public UsageFeeConditionDuplicatedError(@NonNull List<UsageFeeCondition> errorUsageFeeConditions) {
        super("利用料金条件が重複しています。");
        this.errorUsageFeeConditions.addAll(errorUsageFeeConditions);
    }
}
