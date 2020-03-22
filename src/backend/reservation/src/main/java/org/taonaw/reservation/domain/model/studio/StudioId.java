package org.taonaw.reservation.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@EqualsAndHashCode
public class StudioId {
    private final String value;

    public StudioId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Studio id is required.");
        this.value = value;
    }
}
