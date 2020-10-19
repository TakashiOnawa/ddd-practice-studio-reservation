package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.taonaw.studio_reservation.domain.shared.Assertion;

@Getter
@EqualsAndHashCode
public class HashedPassword {
    private final String value;

    public HashedPassword(String value) {
        Assertion.required(value);
        this.value = value;
    }
}
