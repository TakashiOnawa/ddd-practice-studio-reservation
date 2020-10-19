package org.taonaw.studio_reservation.domain.model.studio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class StudioName {
    private final String value;

    public StudioName(String value) {
        Assertion.argumentRange(value, 1, 50);
        this.value = value;
    }
}
