package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DayTypeCondition extends UsageFeeCondition {
    private final DayType dayType;

    public DayTypeCondition(DayType dayType) {
        Assertion.required(dayType);
        this.dayType = dayType;
    }
}
