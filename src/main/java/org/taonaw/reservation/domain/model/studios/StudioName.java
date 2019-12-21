package org.taonaw.reservation.domain.model.studios;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.common.Assertion;

@Getter
@EqualsAndHashCode
public class StudioName {
    private final String value;

    public StudioName(String value) {
        Assertion.argumentRange(value, 1, 64);
        this.value = value;
    }
}
