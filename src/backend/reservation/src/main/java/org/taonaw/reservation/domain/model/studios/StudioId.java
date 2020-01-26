package org.taonaw.reservation.domain.model.studios;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioId {

    private final String value;

    public StudioId(@NonNull String value) {
        Assertion.argumentNotEmpty(value, "Studio id is required.");
        this.value = value;
    }
}
