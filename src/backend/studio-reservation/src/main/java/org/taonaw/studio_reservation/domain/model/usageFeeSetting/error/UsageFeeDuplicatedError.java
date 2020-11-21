package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFee;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsageFeeDuplicatedError extends Error {
    private final Set<UsageFee> errorUsageFees = new HashSet<>();

    public UsageFeeDuplicatedError(@NonNull List<UsageFee> errorUsageFees) {
        super("利用料金が重複しています。");
        this.errorUsageFees.addAll(errorUsageFees);
    }
}
