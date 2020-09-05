package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class HashedPassword {
    private final String value;

    public HashedPassword(String value) {
        this.value = value;
    }
}
