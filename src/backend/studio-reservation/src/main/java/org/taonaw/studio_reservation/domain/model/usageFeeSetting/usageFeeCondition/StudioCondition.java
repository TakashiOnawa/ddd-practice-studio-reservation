package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeCondition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode(callSuper = true)
public class StudioCondition extends UsageFeeCondition {
    private final StudioId studioId;

    public StudioCondition(UsageFeeConditionType type, StudioId studioId) {
        super(type);
        Assertion.required(studioId);
        this.studioId = studioId;
    }
}
