package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class BasicFeeDuplicatedError extends Error {
    private final List<List<UsageFeeCondition>> basicFeeConditions;

    public BasicFeeDuplicatedError(@NonNull List<List<UsageFeeCondition>> basicFeeConditions) {
        super("基本料金が重複しています。");
        this.basicFeeConditions = new ArrayList<>(basicFeeConditions);
    }

    public List<List<UsageFeeCondition>> getBasicFeeConditions() {
        return basicFeeConditions;
    }
}
