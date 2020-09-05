package org.taonaw.studio_reservation.domain.model.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class HashedPassword {
    private final String value;

    public HashedPassword(@NonNull String value) {
        this.value = value;
    }
}
