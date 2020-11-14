package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import org.taonaw.studio_reservation.domain.shared.exception.Error;

public class UsageFeeConditionTypesDifferentError extends Error {
    public UsageFeeConditionTypesDifferentError() {
        super("利用料金条件種別が異なります。");
    }
}
