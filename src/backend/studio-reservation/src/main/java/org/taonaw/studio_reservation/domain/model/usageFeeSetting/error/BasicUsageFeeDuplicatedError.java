package org.taonaw.studio_reservation.domain.model.usageFeeSetting.error;

import lombok.NonNull;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFee;
import org.taonaw.studio_reservation.domain.shared.exception.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasicUsageFeeDuplicatedError extends Error {
    private final Set<BasicUsageFee> errorBasicUsageFees = new HashSet<>();

    public BasicUsageFeeDuplicatedError(@NonNull List<BasicUsageFee> errorBasicUsageFees) {
        super("基本利用料金が重複しています。");
        this.errorBasicUsageFees.addAll(errorBasicUsageFees);
    }
}
