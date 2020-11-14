package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeConditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class DayTypeCondition implements UsageFeeCondition {
    private final DayTypes dayType;

    public DayTypeCondition(DayTypes dayType) {
        Assertion.required(dayType);
        this.dayType = dayType;
    }
}
