package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class UsageFeeConditionTypeDuplicatedError extends Error {
    private final List<Class<? extends UsageFeeCondition>> errorTypes;

    public UsageFeeConditionTypeDuplicatedError(@NonNull List<Class<? extends UsageFeeCondition>> errorTypes) {
        super("利用料金条件が重複しています。");
        this.errorTypes = new ArrayList<>(errorTypes);
    }

    public List<Class<? extends UsageFeeCondition>> getErrorTypes() {
        return errorTypes;
    }
}
