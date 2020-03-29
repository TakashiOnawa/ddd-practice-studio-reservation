package org.taonaw.facilitymanagement.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.facilitymanagement.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioName {
    private final String value;

    public StudioName(@NonNull String value) {
        Assertion.argumentRange(value, 1, 20);
        this.value = value;
    }
}
