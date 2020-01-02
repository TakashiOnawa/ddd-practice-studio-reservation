package org.taonaw.reservation.domain.model.studios;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class StudioId {
    private final String value;

    public StudioId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "value is required.");
        this.value = value;
    }
}
