package org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting;

import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFee.UsageFee;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition.UsageFeeCondition;

import java.util.ArrayList;
import java.util.List;

public class PackFee {
    private UsageFee usageFee;
    private final List<UsageFeeCondition> usageFeeConditions = new ArrayList<>();
}
