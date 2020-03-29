package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioRoomSize {
    private final double value;

    public StudioRoomSize(double value) {
        Assertion.argumentMin(value, 1);
        this.value = value;
    }
}
