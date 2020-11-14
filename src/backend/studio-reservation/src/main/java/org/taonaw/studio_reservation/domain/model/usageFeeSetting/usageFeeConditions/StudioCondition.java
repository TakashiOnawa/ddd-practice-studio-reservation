package org.taonaw.studio_reservation.domain.model.usageFeeSetting.usageFeeConditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.model.studio.StudioId;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.UsageFeeCondition;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioCondition implements UsageFeeCondition {
    private final StudioId studioId;

    public StudioCondition(StudioId studioId) {
        Assertion.required(studioId);
        this.studioId = studioId;
    }
}
